package com.nyualpha.tododiary.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
import com.nyualpha.tododiary.dto.block.UpdateBlockDto;
import com.nyualpha.tododiary.mapper.BlockMapperService;
import com.nyualpha.tododiary.models.Block;
import com.nyualpha.tododiary.repositories.BlockRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BlockService implements IBlockService{

    private BlockRepository blockRepository;
    private BlockMapperService blockMapperService;

    
    public BlockService(BlockRepository blockRepository, BlockMapperService blockMapperService) {
        this.blockRepository = blockRepository;
        this.blockMapperService = blockMapperService;
    }


    /*
     * Create a new block
     */
    @Override
    @Transactional
    public ResponseBlockDto createBlock(CreateBlockDto createBlockDto) {

        Block block = blockMapperService.mapDtoToEntity(createBlockDto, new Block());
        block = blockRepository.save(block);

        return blockMapperService.mapEntityToResponse(block,new ResponseBlockDto());
    }


    @Override
    @Transactional
    public ResponseBlockDto updateBlock(UpdateBlockDto updateBlockDto) {

        Block block = blockRepository.findById(updateBlockDto.getId()).orElseThrow(
                        () -> new EntityNotFoundException("Block not found"));
        
        block = blockMapperService.mapDtoToEntity(updateBlockDto, block);
        block = blockRepository.save(block);

        return blockMapperService.mapEntityToResponse(block,new ResponseBlockDto());
    }

}
