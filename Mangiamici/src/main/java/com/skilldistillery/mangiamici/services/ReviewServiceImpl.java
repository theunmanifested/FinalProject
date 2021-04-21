package com.skilldistillery.mangiamici.services;

import org.springframework.stereotype.Service;

import com.skilldistillery.mangiamici.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepo;
}
