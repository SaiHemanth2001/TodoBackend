package com.nirvedha.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvedha.todo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);

	@Query(value=" SELECT EXISTS(SELECT * FROM users e where e.user_id = ?1)", nativeQuery = true)
	long userExists(long user_id);
}
