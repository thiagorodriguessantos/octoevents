package com.jaya.octoevents.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(onlyExplicitlyIncluded = true)
public class Issue extends Payload{

	@Id
	private Integer id;

	@JsonProperty("repository_url")
	@Size(max=400)
	private String repositoryUrl;
	
	@JsonProperty("labels_url")
	@Size(max=400)
	private String labelsUrl;
	
	@JsonProperty("comments_url")
	@Size(max=400)
	private String commentsUrl;
	
	@NotNull
	@Column(unique = true)	
	private Integer number;
	
	@Size(max=300)
	private String title;
	
	private String state;
	private boolean locked;
	
	@Size(max=500)
	private String assignee;
	
	@Size(max=500)
	private String milestone;
	
	private Integer comments;
	
	@JsonProperty("created_at") private Date createdAt;
	@JsonProperty("updated_at") private Date updatedAt;
	@JsonProperty("closed_at") private Date closedAt;
	
	@JsonProperty("author_association")
	@Size(max=400)
	private String authorAssociation;
	
	@Size(max=400)
	private String body;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy="issue", fetch = FetchType.EAGER)
	private List<IssueEvent> events;
	
}
