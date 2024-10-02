package com.nyualpha.tododiary.services;

import com.nyualpha.tododiary.dto.task.CreateTaskDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;

public interface ITaskService {

    ResponseTaskDto createTask(CreateTaskDto createTaskDto);
}
