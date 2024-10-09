package com.nyualpha.tododiary.mapper;




import org.springframework.stereotype.Service;
import com.nyualpha.tododiary.dto.task.CreateTaskDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;
import com.nyualpha.tododiary.models.Task;


@Service
public class TaskMapperService {


    public Task mapDtoToEntity(CreateTaskDto dto) {

        Task entity = new Task();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setDifficulty(dto.getDifficulty());
        entity.setImportance(dto.getImportance());
        entity.setDeadline(dto.getDeadline());

        return entity;
    }

    

    public ResponseTaskDto mapEntityToResponse(Task entity) {

        ResponseTaskDto dto = new ResponseTaskDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setDifficulty(entity.getDifficulty());
        dto.setImportance(entity.getImportance());
        dto.setDeadline(entity.getDeadline());
        dto.setTaskOrder(entity.getTaskOrder());
        dto.setBlockId(entity.getBlock().getId());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }
}
