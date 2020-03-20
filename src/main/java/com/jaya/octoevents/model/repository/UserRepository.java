package com.jaya.octoevents.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaya.octoevents.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
