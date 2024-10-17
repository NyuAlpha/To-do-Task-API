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
        entity.setDeadline(dto.getDeadline());

        return entity;
    }

    

    public ResponseTaskDto mapEntityToResponseSimple(Task entity) {

        ResponseTaskDto dto = new ResponseTaskDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setDeadline(entity.getDeadline());
        dto.setTaskOrder(entity.getTaskOrder());
        dto.setBlockId(entity.getBlock().getId());
        dto.setCreatedAt(entity.getCreatedAt());


        return dto;
    }


    // public ResponseTaskDto mapEntityToResponseAll(Task entity){

    //     ResponseTaskDto responseTaskDto = mapEntityToResponseSimple(entity);

    //     List<ResponseTaskDto> subtasks = entity.getSubtasks().stream()
    //                     .map(ent -> mapEntityToResponseAll(ent))
    //                     .sorted(Comparator.comparingInt(ResponseTaskDto::getTaskOrder))
    //                     .toList();
    //     responseTaskDto.setSubtasks(subtasks);


    //     return responseTaskDto;
    // }
}
