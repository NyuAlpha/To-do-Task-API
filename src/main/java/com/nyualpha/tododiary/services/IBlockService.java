package com.nyualpha.tododiary.services;

import java.util.List;

import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.dto.block.ResponseBlockDto;
import com.nyualpha.tododiary.dto.block.UpdateBlockDto;

public interface IBlockService {


    ResponseBlockDto getBlock(Long id);

    List<ResponseBlockDto> getAll();

    ResponseBlockDto createBlock(CreateBlockDto createBlockDto);

    ResponseBlockDto updateBlock(Long id, UpdateBlockDto updateBlockDto);

    void delete(Long id);

}
