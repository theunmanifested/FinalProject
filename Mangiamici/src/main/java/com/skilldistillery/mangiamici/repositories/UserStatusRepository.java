package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.UserStatus;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

}
