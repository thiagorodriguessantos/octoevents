package com.jaya.octoevents.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class Payload {

	@JsonProperty("node_id") 
	private String nodeId;
	
	@Size(max=400)
	private String url;
	
	@JsonProperty("html_url")
	@Size(max=400)
	private String htmlUrl;
	
	@JsonProperty("events_url")
	@Size(max=400)
	private String eventsUrl;
	
}
