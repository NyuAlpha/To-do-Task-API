package com.nyualpha.tododiary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nyualpha.tododiary.models.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

}
