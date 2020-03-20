package com.jaya.octoevents.model.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaya.octoevents.model.EndPointError;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndPointErrorRepositoryTest {

	@Inject
	private EndPointErrorRepository endPointErrorRepository;
	
	private static EndPointError endPointErrorToTest;
	
	@BeforeAll
	static void initAll() {
		endPointErrorToTest = createEndPointErrorToTest();
	}
	
	private static EndPointError createEndPointErrorToTest() {
		EndPointError endPointError = new EndPointError();
		endPointError.setClientInfo("Client Info 1");
		endPointError.setDataHora(new Date());
		endPointError.setMessage("Message 1");
		endPointError.setStackTrace("StackeTrace 1");
		return endPointError;
	}
	
	@Test
	@Order(1)
	void testInsertAndSearchEndPointError() {
		EndPointError endPointErrorSaved = endPointErrorRepository.save(endPointErrorToTest);
		EndPointError endPointErrorActual = endPointErrorRepository.findById(endPointErrorSaved.getId()).orElse(null);
		assertNotNull(endPointErrorActual);
		assertEquals(endPointErrorSaved.getId(), endPointErrorActual.getId());
		assertNotNull(endPointErrorActual.getClientInfo());
		assertNotNull(endPointErrorActual.getDataHora());
		assertNotNull(endPointErrorActual.getId());
		assertNotNull(endPointErrorActual.getMessage());
		assertNotNull(endPointErrorActual.getStackTrace());
	}
	
	@Test
	@Order(2)
	void testUpdateEndPointError() {
		endPointErrorToTest.setClientInfo("Client info teste 2");
		endPointErrorToTest.setMessage("Message teste 2");
		endPointErrorToTest.setStackTrace("Stack trace teste 2");
		endPointErrorToTest.setDataHora(null);
		EndPointError endPointErrorUpdated = endPointErrorRepository.save(endPointErrorToTest);
		EndPointError endPointErrorActual = endPointErrorRepository.findById(endPointErrorUpdated.getId()).orElse(null);
		assertNotNull(endPointErrorActual);
		assertEquals(endPointErrorUpdated.getId(), endPointErrorActual.getId());
		assertEquals(endPointErrorActual.getClientInfo(), endPointErrorUpdated.getClientInfo());
		assertEquals(endPointErrorActual.getMessage(),endPointErrorUpdated.getMessage());
		assertEquals(endPointErrorActual.getStackTrace(),endPointErrorUpdated.getStackTrace());		
		assertNull(endPointErrorActual.getDataHora());
	}

	@Test
	@Order(3)
	void testDeleteEndPointError() {
		endPointErrorRepository.delete(endPointErrorToTest);
		EndPointError endPointErrorActual = endPointErrorRepository.findById(endPointErrorToTest.getId()).orElse(null);
		assertNull(endPointErrorActual);
	}

}
