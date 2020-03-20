package com.jaya.octoevents.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
	
	/**
	 * Triggered when a check run is created, rerequested, completed, or has a requested_action
	 */
	CHECK_RUN,
	
	/**
	 * Triggered when a check suite is completed, requested, or rerequested.
	 */
	CHECK_SUITE,
	
	/**
	 * Triggered when a commit comment is created.
	 */
	COMMIT_COMMENT,
	
	/**
	 * Triggered when the body or comment of an issue or pull request includes a URL that matches 
	 * a configured content reference domain. Only GitHub Apps can receive this event.
	 */
	CONTENT_REFERENCE,
	
	/**
	 * Represents a created branch or tag.
	 */
	CREATE,
	
	/**
	 * Represents a deleted branch or tag.
	 */
	DELETE,
	
	/**
	 * Triggered when a deploy key is added or removed from a repository.
	 */
	DEPLOY_KEY,
			
	/**
	 * Represents a deployment.
	 */
	DEPLOYMENT,
	
	/**
	 * Represents a deployment status.
	 */
	DEPLOYMENT_STATUS,
	
	/**
	 * Triggered when a user forks a repository.
	 */
	FORK,
	
	/**
	 * Triggered when someone revokes their authorization of a GitHub App.
	 */
	GIT_HUB_APP_AUTORIZATION,
	
	/**
	 * Triggered when a Wiki page is created or updated.
	 */
	GOLLUM,
	
	/**
	 * Triggered when someone installs (created) , uninstalls (deleted), or accepts new permissions 
	 * (new_permissions_accepted) for a GitHub App. When a GitHub App owner requests new permissions, 
	 * the person who installed the GitHub App must accept the new permissions request.
	 */
	INSTALLATION,
	
	/**
	 * Triggered when a repository is added or removed from an installation.
	 */
	INSTALLATION_REPOSITORIES,
	
	/**
	 * Triggered when an issue comment is created, edited, or deleted.
	 */
	ISSUE_COMMENT,
	
	/**
	 * Triggered when an issue is opened, edited, deleted, pinned, unpinned, closed, reopened, assigned, 
	 * unassigned, labeled, unlabeled, locked, unlocked, transferred, milestoned, or demilestoned.
	 */
	ISSUES,
	
	/**
	 * Triggered when a repository's label is created, edited, or deleted.
	 */
	LABEL,
	
	/**
	 * Triggered when someone purchases a GitHub Marketplace plan, cancels their plan, upgrades their plan 
	 * (effective immediately), downgrades a plan that remains pending until the end of the billing cycle, 
	 * or cancels a pending plan change.
	 */
	MARKETPLACE_PURCHASE,
	
	/**
	 * Triggered when a user accepts an invitation or is removed as a collaborator to a repository, or has 
	 * their permissions changed.
	 */
	MEMBER,
	
	/**
	 * Triggered when a user is added or removed from a team. Organization hooks only.
	 */
	MEMBERSHIP,
	
	/**
	 * Triggered when the webhook that this event is configured on is deleted. 
	 */
	META,
	
	/**
	 * Triggered when a milestone is created, closed, opened, edited, or deleted.
	 */
	MILESTONE,
	
	/**
	 * Triggered when an organization is deleted and renamed, and when a user is added, removed, or invited 
	 * to an organization. Organization hooks only.
	 */
	ORGANIZATION,
	
	/**
	 * Triggered when an organization blocks or unblocks a user. Organization hooks only.
	 */
	ORG_BLOCK,
	
	/**
	 * Triggered on push to a GitHub Pages enabled branch (gh-pages for project pages, 
	 * master for user and organization pages).
	 */
	PAGE_BUILD,
	
	PING,
	
	/**
	 * Triggered when a project card is created, edited, moved, converted to an issue, or deleted.
	 */
	PROJECT_CARD,
	
	/**
	 * Triggered when a project column is created, updated, moved, or deleted.
	 */
	PROJECT_COLUMN,
	
	/**
	 * Triggered when a project is created, updated, closed, reopened, or deleted.
	 */
	PROJECT,
	
	/**
	 * Triggered when a private repository is made public.
	 */
	PUBLIC,
	
	/**
	 * Triggered when a pull request is assigned, unassigned, labeled, unlabeled, opened, edited, closed, 
	 * reopened, synchronize, ready_for_review, locked, unlocked or when a pull request review is 
	 * requested or removed.
	 */
	PULL_REQUEST,
	
	/**
	 * Triggered when a pull request review is submitted into a non-pending state, the body is edited, 
	 * or the review is dismissed.
	 */
	PULL_REQUEST_REVIEW,	
	
	/**
	 * Triggered when a comment on a pull request's unified diff is created, edited, or deleted 
	 * (in the Files Changed tab).
	 */
	PULL_REQUEST_REVIEW_COMMENT,
	
	
	/**
	 * Triggered on a push to a repository branch. Branch pushes and repository tag pushes also trigger 
	 * webhook push events. This is the default event.
	 */
	PUSH,
	
	/**
	 * Triggered when a package version is published or updated in GitHub Packages.
	 */
	PACKAGE,
	
	/**
	 * Triggered when a release is published, unpublished, created, edited, deleted, or prereleased.
	 */
	RELEASE,
	
	/**
	 * Triggered when a repository is created, archived, unarchived, renamed, edited, transferred, made public, 
	 * or made private. Organization hooks are also triggered when a repository is deleted.
	 */
	REPOSITORY,	
	
	/**
	 * Triggered when a successful, cancelled, or failed repository import finishes for a GitHub organization or 
	 * a personal repository.
	 */
	REPOSITORY_IMPORT,

	/**
	 * Triggered when a security alert is created, dismissed, or resolved.
	 */
	REPOSITORY_VULNERABILITY_ALERT,
	
	/**
	 * Triggered when a new security advisory is published, updated, or withdrawn.
	 */
	SECURITY_ADVISORY,
	
	/**
	 * Triggered anytime a sponsorship listing is created, cancelled, edited, or has a tier_changed, 
	 * pending_cancellation, or pending_tier_change. For more information, see "About GitHub Sponsors" 
	 * in the GitHub Help documentation.
	 */
	SPONSORSHIP_EVENT,
	
	/**
	 * Triggered when a star is added or removed from a repository.
	 */
	STAR,
	
	/**
	 * Triggered when the status of a Git commit changes.
	 */
	STATUS,
	
	/**
	 * Triggered when an organization's team is created, deleted, edited, added_to_repository, or 
	 * removed_from_repository. Organization hooks only
	 */
	TEAM,
	
	/**
	 * Triggered when a repository is added to a team.
	 */
	TEAM_ADD,
	
	/**
	 * Triggered when someone stars a repository.
	 */
	WATCH;

	@JsonCreator
	public static EventType forValue(String value) {
	    return EventType.valueOf(value.toUpperCase());
	}	
	
}
