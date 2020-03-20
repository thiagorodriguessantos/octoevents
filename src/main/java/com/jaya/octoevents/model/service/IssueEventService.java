package com.jaya.octoevents.model.service;

import java.util.List;

import com.jaya.octoevents.model.IssueEvent;

public interface IssueEventService {
	
	List<IssueEvent> findEventsByNumberIssue(Integer number);
	
	IssueEvent saveIssueEvent(IssueEvent issueEvent);
	
}
