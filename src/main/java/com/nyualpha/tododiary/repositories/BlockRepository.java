package com.nyualpha.tododiary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nyualpha.tododiary.models.Block;

public interface BlockRepository extends JpaRepository<Block,Long>{


    // public Block findAllAndParentTaskIsNull(Long blockId);
}
