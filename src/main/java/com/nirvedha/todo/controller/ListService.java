package com.nirvedha.todo.controller;



import java.util.List;

import org.springframework.stereotype.Service;

import com.nirvedha.todo.model.TodoModel;
import com.nirvedha.todo.model.User;
import com.nirvedha.todo.repository.TodoRepository;
import com.nirvedha.todo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListService {
	
	private final TodoRepository repository;
	private final UserRepository uRepository;
	
	public TodoModel saveTask (TodoModel task) throws Exception {
		if(uRepository.userExists(task.getUser_id())==1) {
			return repository.save(task);
		}
		else {
			throw new Exception();
		}
	}
	
	public void deleteTask(long id) {
		repository.findById(id).orElseThrow();
		repository.deleteById(id);
	}

	public TodoModel updateTask(TodoModel task, long taskId) {
		TodoModel existingTask = repository.findById(taskId).orElseThrow();
		existingTask.setUser_id(task.getUser_id());
		existingTask.setTask(task.getTask());
		repository.save(existingTask);
		return existingTask;
	}
	
	public List<TodoModel> getListByEmail(String email){
		
		User u = uRepository.findByEmail(email).orElseThrow();
		return repository.getByUserId(u.getUser_id());
	}

	public Long getUserId(String email) {
		User u= uRepository.findByEmail(email).orElseThrow();
		return u.getUser_id();
	}

}
