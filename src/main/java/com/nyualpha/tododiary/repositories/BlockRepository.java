package com.nyualpha.tododiary.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nyualpha.tododiary.models.Block;

public interface BlockRepository extends JpaRepository<Block,Long>{


    // @Query("SELECT DISTINCT b FROM Block b " +
    //             "LEFT JOIN FETCH b.tasks t  " +
    //             "LEFT JOIN FETCH t.subtasks st " +
    //             "WHERE t.parentTask IS NULL")
    // public List<Block> findAllAndTask();

    @EntityGraph(attributePaths = {"tasks"})
    @Query("SELECT b FROM Block b")
    List<Block> findAllWithTasks();

    
    @EntityGraph(attributePaths = {"tasks"})
    @Query("SELECT b FROM Block b " +
       "WHERE b.id = :blockId")
    public Optional<Block> findAllAndTask(@Param("blockId") Long blockId);

}
