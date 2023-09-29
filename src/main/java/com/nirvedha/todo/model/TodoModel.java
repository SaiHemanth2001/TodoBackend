package com.nirvedha.todo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="list")
public class TodoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long task_id;
	@Column(name="user_id")
	private long user_id;
	@Column(name="task")
	private String task;

}
