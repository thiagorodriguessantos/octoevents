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

import com.jaya.octoevents.model.Header;
import com.jaya.octoevents.model.enums.EventType;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HeaderRepositoryTest {

	@Inject
	private HeaderRepository headerRepository;
	
	private static Header header;
	
	@BeforeAll
	static void initAll() {
		header = createHeaderToTest();
	}
	
	private static Header createHeaderToTest() {
		Header header = new Header();
		header.setContentType("application/json");
		header.setEventType(EventType.ISSUES);
		header.setGitHubDelivery("sdfgsdfgsdfg223");
		header.setUserAgent("Agent 01");
		return header;
	}
	
	@Test
	@Order(1)
	void testInsertAndSearchHeader() {
		Header headerSaved = headerRepository.save(header);
		Header headerActual = headerRepository.findById(headerSaved.getId()).orElse(null);
		assertNotNull(headerActual);
		assertEquals(headerSaved.getId(), headerActual.getId());
		assertNotNull(headerActual.getContentType());
		assertNotNull(headerActual.getEventType());
		assertNotNull(headerActual.getGitHubDelivery());
		assertNotNull(headerActual.getUserAgent());
	}
	
	@Test
	@Order(2)
	void testUpdateHeader() {
		header.setContentType("application/html");
		header.setEventType(EventType.CHECK_RUN);
		header.setGitHubDelivery("sdfgsd3");
		header.setUserAgent("Agent 02");
		Header headerUpdated = headerRepository.save(header);
		Header headerActual = headerRepository.findById(headerUpdated.getId()).orElse(null);
		assertNotNull(headerActual);
		assertEquals(headerUpdated.getId(), headerActual.getId());
		assertEquals(headerActual.getContentType(), headerUpdated.getContentType());
		assertEquals(headerActual.getEventType(),headerUpdated.getEventType());
		assertEquals(headerActual.getGitHubDelivery(),headerUpdated.getGitHubDelivery());
		assertEquals(headerActual.getUserAgent(),headerUpdated.getUserAgent());	
	}

	@Test
	@Order(3)
	void testDeleteHeader() {
		headerRepository.delete(header);
		Header HeaderActual = headerRepository.findById(header.getId()).orElse(null);
		assertNull(HeaderActual);
	}

}
