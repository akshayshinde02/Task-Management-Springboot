package com.tasks.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tasks.entity.Task;
import com.tasks.repository.TaskRepo;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") 
public class TaskController {
	
	
	 @Autowired
	    private TaskRepo taskRepository;

	    @GetMapping
	    public List<Task> getAllTasks() {
	        return taskRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
	        return taskRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
	        Task createdTask = taskRepository.save(task);
	        return ResponseEntity.ok(createdTask);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
	        return taskRepository.findById(id)
	                .map(existingTask -> {
	                    existingTask.setTitle(task.getTitle());
	                    existingTask.setDescription(task.getDescription());
	                    existingTask.setCompleted(task.isCompleted());
	                    return ResponseEntity.ok(taskRepository.save(existingTask));
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
	        return taskRepository.findById(id)
	                .map(existingTask -> {
	                    taskRepository.delete(existingTask);
	                    return ResponseEntity.ok().<Void>build();
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }

}
