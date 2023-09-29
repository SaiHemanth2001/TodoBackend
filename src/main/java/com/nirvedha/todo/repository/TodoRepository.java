package com.nirvedha.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nirvedha.todo.model.TodoModel;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
	
	@Query(value="SELECT * FROM list e where e.user_id = ?1", nativeQuery = true)
	List<TodoModel> getByUserId(long user_id);

}
