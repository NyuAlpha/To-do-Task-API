package com.nyualpha.tododiary.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nyualpha.tododiary.dto.CreateBlockDto;
import com.nyualpha.tododiary.models.Block;
import com.nyualpha.tododiary.repositories.BlockRepository;

@Service
public class BlockService implements IBlockService{

    private BlockRepository blockRepository;

    
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }


    /*
     * Create a new block
     */
    @Override
    @Transactional
    public Block createBlock(CreateBlockDto createBlockDto) {

        return blockRepository.save(createBlockDto.toBlock());
    }

}
