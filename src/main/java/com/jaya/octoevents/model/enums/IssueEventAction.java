package com.jaya.octoevents.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum IssueEventAction {
	OPENED, EDITED, DELETED, PINNED, UNPINNED, CLOSED, REOPENED, ASSIGNED, UNASSIGNED, LABELED,
	UNLABELED, LOCKED, UNLOCKED, TRANSFERRED, MILESTONED, DEMILESTONED;
	
	@JsonCreator
	public static IssueEventAction forValue(String value) {
	    return IssueEventAction.valueOf(value.toUpperCase());
	}
		
}
