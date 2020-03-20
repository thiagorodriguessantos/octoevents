package com.jaya.octoevents.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaya.octoevents.model.Header;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Integer>{

	Optional<Header> findByGitHubDelivery(String gitHubDelivery);
}
