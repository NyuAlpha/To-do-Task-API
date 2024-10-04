package com.nyualpha.tododiary.mapper;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import com.nyualpha.tododiary.dto.task.CreateTaskDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;
import com.nyualpha.tododiary.models.Block;
import com.nyualpha.tododiary.models.Task;


@Service
public class TaskMapperService {


    public Task mapDtoToEntity(CreateTaskDto dto, Block block, Task entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setDifficulty(dto.getDifficulty());
        entity.setImportance(dto.getImportance());
        entity.setDeadline(dto.getDeadline());

        entity.setBlock(block);
        Set<Task> tasks = block.getTasks();
        entity.setTaskOrder(tasks.size());
        tasks.add(entity);

        return entity;
    }

    public Task addParentTask(Task entity, Task parentTask) {

        entity.setParentTask(parentTask);
        Set<Task> subtasks = parentTask.getSubtasks();
        entity.setTaskOrder(subtasks.size());
        subtasks.add(entity);
        
        return entity;
    }

    public ResponseTaskDto mapEntityToResponse(Task entity, ResponseTaskDto dto) {

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        dto.setStatus(entity.getStatus());
        dto.setDifficulty(entity.getDifficulty());
        dto.setImportance(entity.getImportance());
        dto.setDeadline(entity.getDeadline());
        dto.setTaskOrder(entity.getTaskOrder());

        if(entity.getBlock() != null)
            dto.setBlockId(entity.getBlock().getId());

        if(entity.getParentTask() != null)  
            dto.setParentTaskId(entity.getParentTask().getId());
     
        if (Hibernate.isInitialized(entity.getSubtasks()) && !entity.getSubtasks().isEmpty()) {
            List<ResponseTaskDto> subtasksDto = entity.getSubtasks()
                                .stream()
                                .map((subtask) -> mapEntityToResponse(subtask, new ResponseTaskDto()))
                                .toList();
            dto.setSubtasks(subtasksDto);
        }

        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}
