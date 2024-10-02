package com.nyualpha.tododiary.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nyualpha.tododiary.dto.task.CreateTaskDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;
import com.nyualpha.tododiary.mapper.TaskMapperService;
import com.nyualpha.tododiary.models.Block;
import com.nyualpha.tododiary.models.Task;
import com.nyualpha.tododiary.repositories.BlockRepository;
import com.nyualpha.tododiary.repositories.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService implements ITaskService{

    private TaskMapperService taskMapperService;
    private TaskRepository taskRepository;
    private BlockRepository blockRepository;


    public TaskService(TaskMapperService taskMapperService, TaskRepository taskRepository,BlockRepository blockRepository) {
        this.taskMapperService = taskMapperService;
        this.taskRepository = taskRepository;
        this.blockRepository = blockRepository;
    }



    @Override
    @Transactional
    public ResponseTaskDto createTask(CreateTaskDto createTaskDto) {
        
        Block block;
        Task parentTask;
        Task task;

        block = blockRepository.findById(createTaskDto.getBlockId()).orElseThrow(
            () ->new EntityNotFoundException("Block not found"));
        
        task = taskMapperService.mapDtoToEntity(createTaskDto,block, new Task());

        if(createTaskDto.getParentTaskId() != null){
            parentTask = taskRepository.findById(createTaskDto.getParentTaskId()).orElseThrow(
                () ->new EntityNotFoundException("ParentTask not found"));

            task = taskMapperService.addParentTask(task, parentTask);
        }

        task = taskRepository.save(task);

        return taskMapperService.mapEntityToResponse(task, new ResponseTaskDto());
    }

}
