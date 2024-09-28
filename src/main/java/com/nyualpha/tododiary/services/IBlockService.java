package com.nyualpha.tododiary.services;

import com.nyualpha.tododiary.dto.CreateBlockDto;
import com.nyualpha.tododiary.models.Block;

public interface IBlockService {


    Block createBlock(CreateBlockDto createBlockDto);

}
