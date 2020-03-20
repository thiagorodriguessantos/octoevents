package com.jaya.octoevents.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.jaya.octoevents.model.enums.IssueEventAction;

import lombok.Data;

@Entity
@Data
public class IssueEvent{

	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore	
	private Integer id;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade=CascadeType.ALL)
	private Header header;
	
	@NotNull
	private IssueEventAction action;
	
	@JsonProperty("created_at")
	private Date eventDateTime;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade=CascadeType.ALL)
	private Issue issue;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade=CascadeType.ALL)
	private GitRepository repository;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade=CascadeType.ALL)
	private User sender;
	
}
