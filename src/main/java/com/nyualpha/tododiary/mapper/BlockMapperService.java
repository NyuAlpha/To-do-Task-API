package com.nyualpha.tododiary.mapper;

import org.springframework.stereotype.Service;
import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
import com.nyualpha.tododiary.dto.block.UpdateBlockDto;
import com.nyualpha.tododiary.models.Block;

@Service
public class BlockMapperService {

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
        dto.setTasks(entity.getTasks());

        return dto;
    }

}