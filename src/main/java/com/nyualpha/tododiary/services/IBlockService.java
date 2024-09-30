package com.nyualpha.tododiary.services;

import com.nyualpha.tododiary.dto.block.CreateBlockDto;
import com.nyualpha.tododiary.models.Block;

public interface IBlockService {


    Block createBlock(CreateBlockDto createBlockDto);

}
