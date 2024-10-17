package com.nyualpha.tododiary.services;


import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


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



    @Override
    @Transactional(readOnly = true)
    public List<ResponseBlockDto> getAll() {
        
        return blockRepository.findAllWithTasks()
                            .stream()
                            .sorted(Comparator.comparing(Block::getCreatedAt))
                            .map((block) -> blockMapperService.mapEntityToResponseSimple(block))
                            .collect(Collectors.toList());
    }





    @Override
    @Transactional(readOnly = true)
    public ResponseBlockDto getBlock(Long id) {

        Block block = blockRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Block not found"));
        return blockMapperService.mapEntityToResponseAll(block);
        
    }



    /*
     * Create a new block
     */
    @Override
    @Transactional
    public ResponseBlockDto createBlock(CreateBlockDto createBlockDto) {

        Block block = blockMapperService.mapDtoToEntity(createBlockDto, new Block());
        block = blockRepository.save(block);

        return blockMapperService.mapEntityToResponseAll(block);
    }




    @Override
    @Transactional
    public ResponseBlockDto updateBlock(Long id ,UpdateBlockDto updateBlockDto) {

        Block block = blockRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Block not found"));
        
        block = blockMapperService.mapDtoToEntity(updateBlockDto, block);
        block = blockRepository.save(block);

        return blockMapperService.mapEntityToResponseAll(block);
    }



    @Override
    @Transactional
    public void delete(Long id) {
        
        checkIfExist(id);
        blockRepository.deleteById(id);
    }



    @Override
    @Transactional(readOnly = true)
    public void checkIfExist(Long id){
        if(!blockRepository.existsById(id)){
            throw new EntityNotFoundException("Block with id " + id + " not found");
        }
    }

}
