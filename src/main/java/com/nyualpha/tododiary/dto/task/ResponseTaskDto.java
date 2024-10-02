package com.nyualpha.tododiary.dto.task;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.nyualpha.tododiary.models.Task;
import com.nyualpha.tododiary.models.attributes.Difficulty;
import com.nyualpha.tododiary.models.attributes.Importance;
import com.nyualpha.tododiary.models.attributes.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
/*
 * DTO with read-only data
 */
public class ResponseTaskDto {


    private Long id;

    private String name;

    private String description;

    private Status status;

    private Importance importance;

    private Difficulty difficulty;

    private Integer taskOrder;

    private LocalDate deadline;

    private LocalDate createdAt;

    private Long blockId;

    private Long parentTaskId;

    private Set<Task> subtasks = new HashSet<>();


}
