package com.nyualpha.tododiary.dto.block;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UpdateBlockDto {


    @NotEmpty(message = "{UpdateBlockDto.name.NotEmpty}")
    @Size(max = 32, message = "{UpdateBlockDto.name.Size}")
    private String name;

    @Size(max = 32,message = "{UpdateBlockDto.category.Size}")
    private String category;

    @Size(max = 128,message = "{UpdateBlockDto.description.Size}")
    private String description;

}