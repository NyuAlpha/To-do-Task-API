package com.nyualpha.tododiary.mapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
import com.nyualpha.tododiary.dto.block.UpdateBlockDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;
import com.nyualpha.tododiary.models.Block;
import com.nyualpha.tododiary.models.Task;

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




    public ResponseBlockDto mapEntityToResponseAll(Block entity) {


        ResponseBlockDto dto = mapEntityToResponseSimple(entity);

        Map<Long, ResponseTaskDto> taskDTOMap = new HashMap<>();
        for (Task task : entity.getTasks()) {
            ResponseTaskDto taskDTO = taskMapperService.mapEntityToResponse(task);
            taskDTOMap.put(task.getId(), taskDTO);
        }

        // Lista para almacenar las tareas de nivel superior
        List<ResponseTaskDto> rootTasks = new ArrayList<>();

        // Asignar sub-tareas a sus tareas padre
        for (Task task : entity.getTasks()) {
            ResponseTaskDto taskDTO = taskDTOMap.get(task.getId());
            if (task.getParentTask() == null) {
                // Tarea de nivel superior
                rootTasks.add(taskDTO);
            } else {
                // Tarea con padre, añadirla a las sub-tareas del padre
                ResponseTaskDto parentDTO = taskDTOMap.get(task.getParentTask().getId());
                if (parentDTO != null) {
                    parentDTO.getSubtasks().add(taskDTO);
                }
            }
        }

        // Ordenar las tareas de nivel superior
        rootTasks.sort(Comparator.comparing(ResponseTaskDto::getTaskOrder));

        // Ordenar recursivamente las sub-tareas
        for (ResponseTaskDto rootTask : rootTasks) {
            sortSubTasks(rootTask);
        }

        dto.setTasks(rootTasks);

        return dto;
    }

    private void sortSubTasks(ResponseTaskDto task) {
        if (task.getSubtasks() != null && !task.getSubtasks().isEmpty()) {
            task.getSubtasks().sort(Comparator.comparing(ResponseTaskDto::getTaskOrder));
            for (ResponseTaskDto subTask : task.getSubtasks()) {
                sortSubTasks(subTask);
            }
        }
    }


    public ResponseBlockDto mapEntityToResponseSimple(Block entity) {

        ResponseBlockDto dto = new ResponseBlockDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

}