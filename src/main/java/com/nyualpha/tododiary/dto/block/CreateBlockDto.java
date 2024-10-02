package com.nyualpha.tododiary.dto.block;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class CreateBlockDto {

    @NotEmpty(message = "{CreateBlockDto.name.NotEmpty}")
    @Size(max = 32, message = "{CreateBlockDto.name.Size}")
    private String name;

    @Size(max = 32,message = "{CreateBlockDto.category.Size}")
    private String category;

    @Size(max = 128,message = "{CreateBlockDto.description.Size}")
    private String description;

}
