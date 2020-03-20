package com.jaya.octoevents.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jaya.octoevents.model.GitRepository;
import com.jaya.octoevents.model.Header;
import com.jaya.octoevents.model.Issue;
import com.jaya.octoevents.model.IssueEvent;
import com.jaya.octoevents.model.User;
import com.jaya.octoevents.model.repository.GitRepoRepository;
import com.jaya.octoevents.model.repository.HeaderRepository;
import com.jaya.octoevents.model.repository.IssueEventRepository;
import com.jaya.octoevents.model.repository.IssueRepository;
import com.jaya.octoevents.model.repository.UserRepository;

@Service
public class IssueEventServiceImpl implements IssueEventService{

	@Inject
	private IssueEventRepository issueEventRepository;
	
	@Inject
	private IssueRepository issueRepository;	
	
	@Inject
	private HeaderRepository headerRepository;
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private GitRepoRepository gitRepoRepository;
	
	public List<IssueEvent> findEventsByNumberIssue(Integer number){
		Optional<Issue> issue = Optional.ofNullable(issueRepository.findByNumber(number)); 
		return issue.map(Issue::getEvents).orElse(new ArrayList<IssueEvent>());
	}
	
	@Transactional
	public IssueEvent saveIssueEvent(IssueEvent issueEvent) {
		
		Optional<Header> header = headerRepository.findByGitHubDelivery(issueEvent.getHeader().getGitHubDelivery());
		if(!header.isPresent()) {
			issueEvent.setEventDateTime(new Date());
			/**
			 * The purpose of the excerpt below is to synchronize the references already saved
			 */
			if(issueEvent.getIssue() != null) {
				Optional<Issue> issue = issueRepository.findById(issueEvent.getIssue().getId());
				issueEvent.setIssue(issue.orElse(issueEvent.getIssue()));
			}
			
			if(issueEvent.getRepository() != null) {
				Optional<GitRepository> repository = gitRepoRepository.findById(issueEvent.getRepository().getId());
				issueEvent.setRepository(repository.orElse(issueEvent.getRepository()));
			}
			
			if(issueEvent.getSender() != null) {
				Optional<User> sender = userRepository.findById(issueEvent.getSender().getId());
				issueEvent.setSender(sender.orElse(issueEvent.getSender()));
			}
			// ------------------------------------------------------------			
			
			issueEventRepository.save(issueEvent);
		}else
			issueEvent = issueEventRepository.findByHeader(header.get()).orElse(issueEvent);
		return issueEvent;
	}
}
