package com.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.entity.User;



public interface UserRepository extends JpaRepository<User, String> 
{
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	

	
	
}