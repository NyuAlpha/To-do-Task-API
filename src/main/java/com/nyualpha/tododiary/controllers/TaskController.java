package com.nyualpha.tododiary.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nyualpha.tododiary.dto.task.CreateTaskDto;
import com.nyualpha.tododiary.dto.task.ResponseTaskDto;
import com.nyualpha.tododiary.services.ITaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blocks/{blockId}/tasks")
public class TaskController {

    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }



    @GetMapping("{taskId}")
    public ResponseEntity<ResponseTaskDto> task(@PathVariable Long blockId, @PathVariable Long taskId){

        return ResponseEntity.ok()
                            .body(taskService.getTask(taskId));
    }

    

    @PostMapping
    public ResponseEntity<ResponseTaskDto> create(
                                    @PathVariable Long blockId,
                                    @RequestParam(value = "parentTaskId", required = false) Long parentTaskId, 
                                    @Valid @RequestBody CreateTaskDto createTaskDto){

        ResponseTaskDto responseDto = taskService.createTask(blockId, parentTaskId, createTaskDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(responseDto.getId())
                                    .toUri();

        return ResponseEntity.created(location)
                            .body(responseDto);
    }


    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long blockId, @PathVariable Long taskId){

        taskService.delete(blockId,taskId);
        return ResponseEntity.noContent().build();
    }

}
