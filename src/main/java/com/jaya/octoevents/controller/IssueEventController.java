package com.jaya.octoevents.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaya.octoevents.model.Header;
import com.jaya.octoevents.model.IssueEvent;
import com.jaya.octoevents.model.enums.EventType;
import com.jaya.octoevents.model.exception.EventNotFoundException;
import com.jaya.octoevents.model.exception.EventUncompatiblePayload;
import com.jaya.octoevents.model.service.IssueEventService;

@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IssueEventController {
	
	@Inject
	private IssueEventService issueEventService;
	
	@GetMapping(path = "/issues/{number}/events")
	@ResponseBody
	public ResponseEntity<List<IssueEvent>> issuesEvents(@PathVariable Integer number){
		List<IssueEvent> issueEvents = issueEventService.findEventsByNumberIssue(number);
		
		if(issueEvents.isEmpty())
			throw new EventNotFoundException();
		
		return ResponseEntity.ok(issueEvents);
	}
	
	@PostMapping("/payload/issues")
	public ResponseEntity<IssueEvent> saveIssueEvent(@RequestHeader Map<String, String> header,
			@RequestBody IssueEvent issueEvent) {
		Header headerEvent = new Header(header.get("content-type"),header.get("user-agent"),header.get("x-github-delivery"),EventType.valueOf(header.get("x-github-event").toUpperCase()));
		if(headerEvent.getEventType() == EventType.ISSUES) {
			issueEvent.setHeader(headerEvent);
			issueEventService.saveIssueEvent(issueEvent);
			return ResponseEntity.ok().build();
		}else 
			throw new EventUncompatiblePayload();
	}
}
