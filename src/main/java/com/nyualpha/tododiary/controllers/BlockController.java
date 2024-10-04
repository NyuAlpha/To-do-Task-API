package com.nyualpha.tododiary.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
import com.nyualpha.tododiary.dto.block.UpdateBlockDto;
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
     * Get all blocks
     */
    @GetMapping
    public ResponseEntity< List<ResponseBlockDto> > getAllBlocks(){
        return ResponseEntity.ok()
                            .body(blockService.getAll());
    }


    /*
     * Creating a new block and saving it to a database
     */
    @PostMapping
    public ResponseEntity<ResponseBlockDto> createBlock(@Valid @RequestBody CreateBlockDto createBlockDto){

        ResponseBlockDto responseDTO = blockService.createBlock(createBlockDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(responseDTO.getId())
                                    .toUri();

        return ResponseEntity.created(location)
                            .body(responseDTO);
    }


    /*
     * Updating a new block and saving it to a database
     */
    @PutMapping
    public ResponseEntity<ResponseBlockDto> updateBlock(@Valid @RequestBody UpdateBlockDto updateBlockDto){

        ResponseBlockDto responseDTO = blockService.updateBlock(updateBlockDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(responseDTO.getId())
                                    .toUri();

        return ResponseEntity.ok()
                            .location(location)
                            .body(responseDTO);
    }
}
