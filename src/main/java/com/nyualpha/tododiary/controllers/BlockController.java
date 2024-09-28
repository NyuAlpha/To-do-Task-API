package com.nyualpha.tododiary.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nyualpha.tododiary.dto.CreateBlockDto;
import com.nyualpha.tododiary.dto.UpdateBlockDto;
import com.nyualpha.tododiary.models.Block;
import com.nyualpha.tododiary.services.IBlockService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/block")
public class BlockController {

    private IBlockService blockService;

    
    public BlockController(IBlockService blockService) {
        this.blockService = blockService;
    }




    /*
     * Probando que funciona
     */
    @PostMapping
    public ResponseEntity<UpdateBlockDto> createBlock(@Valid @RequestBody CreateBlockDto createBlockDto){

        Block block = blockService.createBlock(createBlockDto);

        UpdateBlockDto responseDTO = new UpdateBlockDto();
        responseDTO.setId(block.getId());
        responseDTO.setName(block.getName());
        responseDTO.setCategory(block.getCategory());
        responseDTO.setDescription(block.getDescription());
        responseDTO.setCreatedAt(block.getCreatedAt());
        responseDTO.setTasks(block.getTasks());

        System.out.println(block.getName());

        return ResponseEntity.ok(responseDTO);

    }
}
