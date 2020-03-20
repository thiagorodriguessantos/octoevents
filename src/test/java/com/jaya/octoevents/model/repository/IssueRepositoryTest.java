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

import com.jaya.octoevents.model.Issue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IssueRepositoryTest {

	@Inject
	private IssueRepository issueRepository;
	
	private static Issue issue;
	
	@BeforeAll
	static void initAll() {
		issue = createIssueToTest();
	}
	
	private static Issue createIssueToTest() {
		Issue issue = new Issue();
		issue.setId(9999999);
		issue.setBody("Body 01");
		issue.setRepositoryUrl("http://repo/12121");
		issue.setState("state 01");
		issue.setNumber(9);
		return issue;
	}
	
	@Test
	@Order(1)
	void testInsertAndSearchIssue() {
		Issue issueSaved = issueRepository.save(issue);
		Issue issueActual = issueRepository.findById(issueSaved.getId()).orElse(null);
		assertNotNull(issueActual);
		assertEquals(issueSaved.getId(), issueActual.getId());
		assertNotNull(issueActual.getBody());
		assertNotNull(issueActual.getRepositoryUrl());
		assertNotNull(issueActual.getState());
		assertNotNull(issueActual.getNumber());
	}
	
	@Test
	@Order(2)
	void testUpdateIssue() {
		issue.setBody("Body 02");
		issue.setRepositoryUrl("http://repo/44444");
		issue.setState("state 02");
		issue.setNumber(8);
		Issue issueUpdated = issueRepository.save(issue);
		Issue issueActual = issueRepository.findById(issueUpdated.getId()).orElse(null);
		assertNotNull(issueActual);
		assertEquals(issueUpdated.getId(), issueActual.getId());
		assertEquals(issueActual.getBody(), issueUpdated.getBody());
		assertEquals(issueActual.getRepositoryUrl(),issueUpdated.getRepositoryUrl());
		assertEquals(issueActual.getState(),issueUpdated.getState());
		assertEquals(issueActual.getNumber(),issueUpdated.getNumber());	
	}

	@Test
	@Order(3)
	void testDeleteIssue() {
		issueRepository.delete(issue);
		Issue IssueActual = issueRepository.findById(issue.getId()).orElse(null);
		assertNull(IssueActual);
	}
}
