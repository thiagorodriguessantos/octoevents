package com.jaya.octoevents.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaya.octoevents.model.EndPointError;
import com.jaya.octoevents.model.repository.EndPointErrorRepository;

@SpringBootTest
public class EndPointErrorServiceTest {

	@Inject
	private EndPointErrorService endPointErrorService;
	
	@Inject
	private EndPointErrorRepository endPointErrorRepository;	
	
	private static EndPointError createEndPointErrorToTest() {
		EndPointError endPointError = new EndPointError();
		endPointError.setClientInfo("Client Info 1");
		endPointError.setDataHora(new Date());
		endPointError.setMessage("Message 1");
		endPointError.setStackTrace("StackeTrace 1");
		return endPointError;
	}
	
	@Test
	void testPersistError() {
		EndPointError endPointErrorSaved = endPointErrorService.persistError(createEndPointErrorToTest());
		EndPointError endPointErrorActual = endPointErrorRepository.findById(endPointErrorSaved.getId()).orElse(null);
		assertNotNull(endPointErrorActual);
		assertEquals(endPointErrorSaved.getId(), endPointErrorActual.getId());
		assertNotNull(endPointErrorActual.getClientInfo());
		assertNotNull(endPointErrorActual.getDataHora());
		assertNotNull(endPointErrorActual.getId());
		assertNotNull(endPointErrorActual.getMessage());
		assertNotNull(endPointErrorActual.getStackTrace());
		endPointErrorRepository.delete(endPointErrorActual);
	}
	

}
