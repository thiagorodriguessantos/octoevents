package com.jaya.octoevents.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IssueEventRepositoryTest {

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
	void testInsertAndSearchIssueEvent() {
		IssueEvent issueEventSaved = issueEventRepository.save(issueEvent);
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
	void testUpdateIssueEvent() {
		
		IssueEvent issueEvent = new IssueEvent();
		issueEvent.setAction(IssueEventAction.DELETED);
		
		IssueEvent issueEventUpdated = issueEventRepository.save(issueEvent);
		IssueEvent issueEventActual = issueEventRepository.findById(issueEventUpdated.getId()).orElse(null);
		assertNotNull(issueEventActual);
		assertNotNull(issueEventActual.getAction());
	}

	@Test
	@Order(3)
	void testDeleteIssueEvent() {
		issueEventRepository.delete(issueEvent);
		IssueEvent IssueEventActual = issueEventRepository.findById(issueEvent.getId()).orElse(null);
		assertNull(IssueEventActual);
	}

}
