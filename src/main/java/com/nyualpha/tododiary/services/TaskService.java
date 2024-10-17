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
    private BlockService blockService;


    public TaskService(TaskMapperService taskMapperService, TaskRepository taskRepository,
                        BlockRepository blockRepository,BlockService blockService) {
        this.taskMapperService = taskMapperService;
        this.taskRepository = taskRepository;
        this.blockRepository = blockRepository;
        this.blockService = blockService;
    }


    @Override
    @Transactional(readOnly = true)
    public ResponseTaskDto getTask(Long id){

        Task task = taskRepository.findById(id).orElseThrow(
                () ->new EntityNotFoundException("ParentTask not found"));
        
        ResponseTaskDto responseTaskDto = taskMapperService.mapEntityToResponseSimple(task);
        return responseTaskDto;
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

        return taskMapperService.mapEntityToResponseSimple(task);
    }


    @Override
    @Transactional
    public void delete(Long blockId, Long taskId){

        // Verifica si el bloque existe
        blockService.checkIfExist(blockId);

        checkIfExist(blockId, taskId);

        // Si las verificaciones pasan, elimina la tarea
        taskRepository.deleteById(taskId);

    }


    
    @Transactional(readOnly = true)
    private void checkIfExist(Long blockId, Long taskId){
        // Verifica si la tarea existe y pertenece al bloque
        if (!taskRepository.existsByIdAndBlockId(taskId, blockId)) {
            throw new EntityNotFoundException("Task with id " + taskId + " not found in block " + blockId);
        }
    }
}
