package com.jaya.octoevents.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jaya.octoevents.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer>{
	
	public Issue findByNumber(Integer number);
	
}