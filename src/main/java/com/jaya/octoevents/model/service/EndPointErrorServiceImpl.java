package com.jaya.octoevents.model.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jaya.octoevents.model.EndPointError;
import com.jaya.octoevents.model.repository.EndPointErrorRepository;

@Service
public class EndPointErrorServiceImpl implements EndPointErrorService{

	@Inject
	private EndPointErrorRepository errorRepository;
	
	@Transactional
	public EndPointError persistError(EndPointError error) {
		return errorRepository.save(error);
	}
	
}
