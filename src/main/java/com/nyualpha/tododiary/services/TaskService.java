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
    public ResponseTaskDto createTask(Long blockId, Long parentTaskId,CreateTaskDto createTaskDto) {
        

        Block block = blockRepository.findById(blockId).orElseThrow(
            () ->new EntityNotFoundException("Block not found"));
        
        Task task = taskMapperService.mapDtoToEntity(createTaskDto);
        

        if(parentTaskId != null){

            Task parentTask = taskRepository.findById(parentTaskId).orElseThrow(
                () ->new EntityNotFoundException("ParentTask not found"));

            /*First we assign the order to prever error DataIntegrityViolationException */
            task.setTaskOrder(taskRepository.getOrderByBlockIdAndParentTask(blockId, parentTaskId) + 1);
            task.setParentTask(parentTask);
            
        }else{
            task.setTaskOrder(taskRepository.getOrderByBlockId(blockId) + 1);
        }

        task.setBlock(block);
        task = taskRepository.save(task);

        return taskMapperService.mapEntityToResponse(task);
    }

}
