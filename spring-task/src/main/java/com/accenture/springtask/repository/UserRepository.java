package com.accenture.springtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.springtask.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
