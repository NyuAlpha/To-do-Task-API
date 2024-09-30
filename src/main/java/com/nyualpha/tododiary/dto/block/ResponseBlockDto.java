package com.nyualpha.tododiary.dto.block;


import java.time.LocalDate;
import java.util.Set;

import com.nyualpha.tododiary.models.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
/*
 * DTO with read-only data
 */
public class ResponseBlockDto {

    private Long id;

    private String name;

    private String category;

    private String description;

    private LocalDate createdAt;

    private Set<Task> tasks;

}
