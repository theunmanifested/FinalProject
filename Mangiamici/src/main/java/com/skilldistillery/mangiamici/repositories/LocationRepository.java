package com.skilldistillery.mangiamici.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mangiamici.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
