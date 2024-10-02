package com.nyualpha.tododiary.dto.block;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UpdateBlockDto {

    @NotNull
    @Min(value = 0)
    @Max(value = Long.MAX_VALUE)
    private Long id;

    @NotEmpty
    @Size(max = 32)
    private String name;

    @Size(max = 32)
    private String category;

    @Size(max = 128)
    private String description;

}