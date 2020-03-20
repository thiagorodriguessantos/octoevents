package com.jaya.octoevents.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends Payload{

	@Id
	private Integer id;
	
	private String login;
	
	@JsonProperty("avatar_url") 
	@Size(max=400)
	private String avatarUrl;
	
	@JsonProperty("gravatar_id")
	@Size(max=500)
	private String gravatarId;
	
	@JsonProperty("followers_url")
	@Size(max=400)
	private String followersUrl;
	
	@JsonProperty("following_url")
	@Size(max=400)
	private String followingUrl;
	
	@JsonProperty("gists_url")
	@Size(max=400)
	private String gistsUrl;
	
	@JsonProperty("starred_url")
	@Size(max=400)
	private String starredUrl;
	
	@JsonProperty("subscriptions_url")
	@Size(max=400)
	private String subscriptionsUrl;
	
	@JsonProperty("organizations_url")
	@Size(max=400)
	private String organizationsUrl;
	
	@JsonProperty("repos_url")
	@Size(max=400)
	private String reposUrl;
	
	@JsonProperty("received_events_url")
	@Size(max=400)
	private String receivedEventsUrl;
	
	private String type;
	
	@JsonProperty("site_admin")
	@Size(max=400)
	private String siteAdmin;

}
