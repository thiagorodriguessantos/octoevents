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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GitRepoRepositoryTest {

	@Inject
	private GitRepoRepository gitRepoRepository;
	
	private static GitRepository gitRepository;
	
	@BeforeAll
	static void initAll() {
		gitRepository = createGitRepositoryToTest();
	}
	
	private static GitRepository createGitRepositoryToTest() {
		GitRepository gitRepository = new GitRepository();
		gitRepository.setId(999999999);
		gitRepository.setArchived(false);
		gitRepository.setArchiveUrl("http://12345.com");
		gitRepository.setAssigneesUrl("http://q234123412.com");
		gitRepository.setFullName("Full Name 1");
		return gitRepository;
	}
	
	@Test
	@Order(1)
	void testInsertAndSearchGitRepository() {
		GitRepository gitRepositorySaved = gitRepoRepository.save(gitRepository);
		GitRepository gitRepositoryActual = gitRepoRepository.findById(gitRepositorySaved.getId()).orElse(null);
		assertNotNull(gitRepositoryActual);
		assertEquals(gitRepositorySaved.getId(), gitRepositoryActual.getId());
		assertNotNull(gitRepositoryActual.getArchiveUrl());
		assertNotNull(gitRepositoryActual.getAssigneesUrl());
		assertNotNull(gitRepositoryActual.getFullName());
	}
	
	@Test
	@Order(2)
	void testUpdateGitRepository() {
		gitRepository.setArchived(true);
		gitRepository.setArchiveUrl("http://sdfrghsdfg.com");
		gitRepository.setAssigneesUrl("http://q2.com");
		gitRepository.setFullName("Full Name 2");
		GitRepository gitRepositoryUpdated = gitRepoRepository.save(gitRepository);
		GitRepository gitRepositoryActual = gitRepoRepository.findById(gitRepositoryUpdated.getId()).orElse(null);
		assertNotNull(gitRepositoryActual);
		assertEquals(gitRepositoryUpdated.getId(), gitRepositoryActual.getId());
		assertEquals(gitRepositoryActual.getArchiveUrl(), gitRepositoryUpdated.getArchiveUrl());
		assertEquals(gitRepositoryActual.getAssigneesUrl(),gitRepositoryUpdated.getAssigneesUrl());
		assertEquals(gitRepositoryActual.getFullName(),gitRepositoryUpdated.getFullName());		
	}

	@Test
	@Order(3)
	void testDeleteGitRepository() {
		gitRepoRepository.delete(gitRepository);
		GitRepository GitRepositoryActual = gitRepoRepository.findById(gitRepository.getId()).orElse(null);
		assertNull(GitRepositoryActual);
	}

}
