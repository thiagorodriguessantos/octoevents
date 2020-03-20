package com.jaya.octoevents.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaya.octoevents.model.GitRepository;
import com.jaya.octoevents.model.Header;
import com.jaya.octoevents.model.Issue;
import com.jaya.octoevents.model.IssueEvent;
import com.jaya.octoevents.model.User;
import com.jaya.octoevents.model.enums.EventType;
import com.jaya.octoevents.model.enums.IssueEventAction;
import com.jaya.octoevents.model.repository.IssueEventRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IssueEventServiceTest {

	@Inject
	private IssueEventService issueEventService;
	
	@Inject
	private IssueEventRepository issueEventRepository;
	
	private static IssueEvent issueEvent;
	
	@BeforeAll
	static void initAll() {
		issueEvent = createIssueEventToTest();
	}
	
	private static IssueEvent createIssueEventToTest() {
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
		
		IssueEvent issueEvent = new IssueEvent();
		issueEvent.setHeader(header);
		issueEvent.setAction(IssueEventAction.OPENED);
		issueEvent.setIssue(issue);
		issueEvent.setRepository(gitRepository);
		issueEvent.setSender(user);
		return issueEvent;
	}

	@Test
	@Order(1)
	void testSaveIssueEvent() {
		IssueEvent issueEventSaved = issueEventService.saveIssueEvent(issueEvent);
		IssueEvent issueEventActual = issueEventRepository.findById(issueEventSaved.getId()).orElse(null);
		assertNotNull(issueEventActual);
		assertEquals(issueEventSaved.getId(), issueEventActual.getId());
		assertNotNull(issueEventActual.getHeader());
		assertNotNull(issueEventActual.getAction());
		assertNotNull(issueEventActual.getIssue());
		assertNotNull(issueEventActual.getRepository());
		assertNotNull(issueEventActual.getSender());		
	}

	@Test
	@Order(2)
	void testFindEventsByNumberIssue() {
		List<IssueEvent> issuesEvents = issueEventService.findEventsByNumberIssue(issueEvent.getIssue().getNumber());
		assertTrue(!issuesEvents.isEmpty());
		issueEventRepository.delete(issueEvent);
	}
}
