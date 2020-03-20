package com.jaya.octoevents.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaya.octoevents.model.Header;
import com.jaya.octoevents.model.IssueEvent;

@Repository
public interface IssueEventRepository extends JpaRepository<IssueEvent, Integer>{

	Optional<IssueEvent> findByHeader(Header header);
	
}
