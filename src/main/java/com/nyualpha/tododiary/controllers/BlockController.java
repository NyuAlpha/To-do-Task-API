package com.nyualpha.tododiary.controllers;


import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
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
     * Creating a new block and saving it to a database
     */
    @PostMapping
    public ResponseEntity<ResponseBlockDto> createBlock(@Valid @RequestBody CreateBlockDto createBlockDto){

        Block block = blockService.createBlock(createBlockDto);
        ResponseBlockDto responseDTO = block.toResponseDto();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(responseDTO.getId())
                                    .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }
}
