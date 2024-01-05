package com.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasks.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
    
}
