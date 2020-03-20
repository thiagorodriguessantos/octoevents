package com.jaya.octoevents.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class GitRepository extends Payload{

	@Id
	private Integer id;

	@Size(max=300)
	private String name;
	
	@JsonProperty("full_name")
	@Size(max=400)
	private String fullName;
	
	@JsonProperty("private") private boolean privateRepository;
	
	@Size(max=400)
	private String description;
	
	@Size(max=400)
	private String fork;
	
	@JsonProperty("forks_url")
	@Size(max=400)
	private String forksUrl;
	
	@JsonProperty("keys_url")
	@Size(max=400)
	private String keysUrl;
	
	@JsonProperty("collaborators_url")
	@Size(max=400)
	private String collaboratorsUrl;
	
	@JsonProperty("teams_url")
	@Size(max=400)
	private String teamsUrl;
	
	@JsonProperty("hooks_url")
	@Size(max=400)
	private String hooksUrl;
	
	@JsonProperty("issue_events_url")
	@Size(max=400)
	private String issueEventsUrl;
	
	@JsonProperty("assignees_url")
	@Size(max=400)
	private String assigneesUrl;
	
	@JsonProperty("branches_url")
	@Size(max=400)
	private String branchesUrl;
	
	@JsonProperty("tags_url")
	@Size(max=400)
	private String tagsUrl;
	
	@JsonProperty("blobs_url")
	@Size(max=400)
	private String blobsUrl;
	
	@JsonProperty("git_tags_url")
	@Size(max=400)
	private String gitTagsUrl;
	
	@JsonProperty("git_refs_url")
	@Size(max=400)
	private String gitRefsUrl;
	
	@JsonProperty("trees_url")
	@Size(max=400)
	private String treesUrl;
	
	@JsonProperty("languages_url")
	@Size(max=400)
	private String languagesUrl;
	
	@JsonProperty("stargazes_url")
	@Size(max=400)
	private String stargazersUrl;
	
	@JsonProperty("contributors_url")
	@Size(max=400)
	private String contributorsUrl;
	
	@JsonProperty("subscribers_url")
	@Size(max=400)
	private String subscribersUrl;
	
	@JsonProperty("subscription_url")
	@Size(max=400)
	private String subscriptionUrl;
	
	@JsonProperty("commits_url")
	@Size(max=400)
	private String commitsUrl;
	
	@JsonProperty("git_commits_url")
	@Size(max=400)
	private String gitCommitsUrl;
	
	@JsonProperty("comments_url")
	@Size(max=400)
	private String commentsUrl;
	
	@JsonProperty("issue_comment_url")
	@Size(max=400)
	private String issueCommentUrl;
	
	@JsonProperty("contents_url")
	@Size(max=400)
	private String contentsUrl;
	
	@JsonProperty("compare_url")
	@Size(max=400)
	private String compareUrl;
	
	@JsonProperty("merges_url")
	@Size(max=400)
	private String mergesUrl;
	
	@JsonProperty("archive_url")
	@Size(max=400)
	private String archiveUrl;
	
	@JsonProperty("downloads_url")
	@Size(max=400)
	private String downloadsUrl;
	
	@JsonProperty("issues_url")
	@Size(max=400)
	private String issuesUrl;
	
	@JsonProperty("pulls_url")
	@Size(max=400)
	private String pullsUrl;
	
	@JsonProperty("milestones_url")
	@Size(max=400)
	private String milestonesUrl;
	
	@JsonProperty("notifications_url")
	@Size(max=400)
	private String notificationsUrl;
	
	@JsonProperty("labels_url")
	@Size(max=400)
	private String labelsUrl;
	
	@JsonProperty("releases_url")
	@Size(max=400)
	private String releasesUrl;
	
	@JsonProperty("deployments_url")
	@Size(max=400)
	private String deploymentsUrl;
	
	@JsonProperty("created_at")	private Date createdAt;
	@JsonProperty("updated_at") private Date updatedAt;
	@JsonProperty("pushed_at") private Date pushedAt;
	
	@JsonProperty("git_url")
	@Size(max=400)
	private String gitUrl;
	
	@JsonProperty("ssh_url")
	@Size(max=400)
	private String sshUrl;
	
	@JsonProperty("clone_url")
	@Size(max=400)
	private String cloneUrl;
	
	@JsonProperty("svn_url")
	@Size(max=400)
	private String svnUrl;
	
	@Size(max=400)
	private String homepage;
	
	private Integer size;
	@JsonProperty("watchers_count") private String watchersCount;
	private String language;
	@JsonProperty("has_issues") private boolean hasIssues;
	@JsonProperty("has_projects") private boolean hasProjects;
	@JsonProperty("has_downloads") private boolean hasDownloads;
	@JsonProperty("has_wiki") private boolean hasWiki;
	@JsonProperty("has_pages") private boolean hasPages;
	@JsonProperty("forks_count") private Integer forksCount;
	
	@JsonProperty("mirror_url")
	@Size(max=400)
	private String mirrorUrl;
	
	private boolean archived;
	private boolean disabled;
	@JsonProperty("open_issues_count") private Integer openIssuesCount;
	private String license;
	private Integer forks;
	@JsonProperty("open_issues") private Integer opnenIssues;
	private Integer watchers;
	
	@JsonProperty("default_branch")
	@Size(max=400)
	private String defaultBranch;
	
}
