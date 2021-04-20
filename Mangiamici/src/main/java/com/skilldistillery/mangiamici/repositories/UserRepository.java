package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
