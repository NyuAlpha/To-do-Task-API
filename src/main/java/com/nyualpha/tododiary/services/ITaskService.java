package com.nyualpha.tododiary.services;

import com.nyualpha.tododiary.dto.task.CreateTaskDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;

public interface ITaskService {

    ResponseTaskDto getTask(Long id);

    ResponseTaskDto createTask(Long blockId, Long parentTaksId, CreateTaskDto createTaskDto);

    void delete(Long blockId, Long TaskId);

}
