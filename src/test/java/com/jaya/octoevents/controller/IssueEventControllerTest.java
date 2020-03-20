package com.jaya.octoevents.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.jaya.octoevents.model.GitRepository;
import com.jaya.octoevents.model.Header;
import com.jaya.octoevents.model.Issue;
import com.jaya.octoevents.model.IssueEvent;
import com.jaya.octoevents.model.User;
import com.jaya.octoevents.model.enums.EventType;
import com.jaya.octoevents.model.enums.IssueEventAction;
import com.jaya.octoevents.model.service.EndPointErrorService;
import com.jaya.octoevents.model.service.IssueEventService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(IssueEventController.class)
@AutoConfigureMockMvc(addFilters=false) 
public class IssueEventControllerTest {
	
	@Inject
	private MockMvc mockMvc;
	
	@MockBean
	private IssueEventService issueEventService;

	@MockBean
	private EndPointErrorService endPointErrorService;
	
	private Issue createIssueToGETTest() {
		Header header = new Header();
		header.setContentType("application/json");
		header.setEventType(EventType.ISSUES);
		header.setGitHubDelivery("sdfgsdfgsdfg223");
		header.setUserAgent("Agent 01");
		
		Issue issue = new Issue();
		issue.setId(9999999);
		issue.setBody("Body 01");
		issue.setRepositoryUrl("http://repo/12121");
		issue.setState("state 01");
		issue.setNumber(9);

		GitRepository gitRepository = new GitRepository();
		gitRepository.setId(999999999);
		gitRepository.setArchived(false);
		gitRepository.setArchiveUrl("http://12345.com");
		gitRepository.setAssigneesUrl("http://q234123412.com");
		gitRepository.setFullName("Full Name 1");
		
		User user = new User();
		user.setId(999999999);
		user.setAvatarUrl("http://avatar.com");
		user.setLogin("master");
		user.setNodeId("Node Id 23452345");
		user.setType("Type");
		
		IssueEvent issueEvent1 = new IssueEvent();
		issueEvent1.setHeader(header);
		issueEvent1.setAction(IssueEventAction.OPENED);
		issueEvent1.setIssue(issue);
		issueEvent1.setRepository(gitRepository);
		issueEvent1.setSender(user);

		IssueEvent issueEvent2 = new IssueEvent();
		issueEvent2.setHeader(header);
		issueEvent2.setAction(IssueEventAction.CLOSED);
		issueEvent2.setIssue(issue);
		issueEvent2.setRepository(gitRepository);
		issueEvent2.setSender(user);
		
		List<IssueEvent> events = new ArrayList<IssueEvent>();
		events.add(issueEvent1);
		events.add(issueEvent2);
		issue.setEvents(events);
		
		return issue;
	}

	@Test
	public void testIssuesEvents() throws Exception{
		
		Issue issue = createIssueToGETTest();
		Integer number = issue.getNumber();
		
        Mockito.when(issueEventService.findEventsByNumberIssue(number)).thenReturn(issue.getEvents());
        
        mockMvc.perform(get("/issues/{number}/events",number)
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].action", CoreMatchers.is(issue.getEvents().get(0).getAction().toString())))
                .andExpect(jsonPath("$.[1].action", CoreMatchers.is(issue.getEvents().get(1).getAction().toString())));
	
	}

}
