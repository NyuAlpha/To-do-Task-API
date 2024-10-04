package com.nyualpha.tododiary.mapper;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
import com.nyualpha.tododiary.dto.block.UpdateBlockDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;
import com.nyualpha.tododiary.models.Block;

@Service
public class BlockMapperService {

    private TaskMapperService taskMapperService;

    


    public BlockMapperService(TaskMapperService taskMapperService) {
        this.taskMapperService = taskMapperService;
    }




    public Block mapDtoToEntity(CreateBlockDto dto, Block entity) {

        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        
        return entity;
    }




    public Block mapDtoToEntity(UpdateBlockDto dto, Block entity) {

        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());

        return entity;
    }




    public ResponseBlockDto mapEntityToResponse(Block entity, ResponseBlockDto dto) {

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());

        if (Hibernate.isInitialized(entity.getTasks()) && !entity.getTasks().isEmpty()) {
            List<ResponseTaskDto> tasksDto = entity.getTasks()
                            .stream()
                            .map(task -> taskMapperService.mapEntityToResponse(task, new ResponseTaskDto()))
                            .toList();
            dto.setTasks(tasksDto);
        }

        return dto;
    }

}