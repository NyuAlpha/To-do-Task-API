package com.nyualpha.tododiary.dto.task;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.nyualpha.tododiary.models.attributes.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CreateTaskDto {

    @NotEmpty
    @Size(max=32)
    private String name;

    @Size(max=128)
    private String description;

    private Status status;

    @DateTimeFormat(pattern = "YYYY-mm-dd")
    private LocalDate deadline;
}
