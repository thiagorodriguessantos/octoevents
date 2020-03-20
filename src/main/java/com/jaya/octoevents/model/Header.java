package com.jaya.octoevents.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaya.octoevents.model.enums.EventType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Header {
	
	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore	
	private Integer id;
	
	@NotNull
	@Size(max=250)
	private String contentType;
	@NotNull
	@Size(max=400)
	private String userAgent;
	@NotNull
	@Size(max=400)
	private String gitHubDelivery;

	@NotNull
	private EventType eventType;

	public Header(String contentType, String userAgent, String gitHubDelivery, EventType eventType) {
		super();
		this.contentType = contentType;
		this.userAgent = userAgent;
		this.gitHubDelivery = gitHubDelivery;
		this.eventType = eventType;
	}
	
}
