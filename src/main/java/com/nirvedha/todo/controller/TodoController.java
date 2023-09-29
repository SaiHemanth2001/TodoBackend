package com.nirvedha.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirvedha.todo.model.TodoModel;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TodoController {
	
	private final ListService service;
	
	@PostMapping("/addtask")
	public TodoModel addTask(@RequestBody TodoModel task) throws Exception{
		return service.saveTask(task);
	}
	
	@PutMapping("editTask/{id}")
	public ResponseEntity<TodoModel> updateEmployee(@PathVariable("id") long taskId
			,@RequestBody TodoModel task){
		return new ResponseEntity<TodoModel>(service.updateTask(task, taskId), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable("id") long taskId){
		service.deleteTask(taskId);
		return "Task Deleted";
	}
	
	@GetMapping("getByEmail/{email}")
	public List<TodoModel> getByEmail(@PathVariable String email){
		return service.getListByEmail(email);
	}
	
	@GetMapping("getUserId/{email}")
	public Long getUserId(@PathVariable String email) {
		return service.getUserId(email);
	}
	
	

}
