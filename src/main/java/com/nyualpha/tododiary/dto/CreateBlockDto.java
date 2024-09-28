package com.nyualpha.tododiary.dto;

import com.nyualpha.tododiary.models.Block;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class CreateBlockDto {

    @NotEmpty
    @Size(max = 64)
    private String name;

    @Size(max = 64)
    private String category;

    @Size(max = 128)
    private String description;



    public Block toBlock(){

        Block block = new Block();

        block.setName(name);
        block.setCategory(category);
        block.setDescription(description);

        return block;
    }



}
