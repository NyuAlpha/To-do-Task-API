package com.nyualpha.tododiary.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nyualpha.tododiary.models.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

    /*When the parent task is null, that is, is a top parent task */
    @Query("SELECT COUNT(t) FROM Task t WHERE t.block.id = :blockId AND t.parentTask.id IS NULL")
    Integer getOrderByBlockId(@Param("blockId") Long blockId);


    /*When the parent task is not null */
    @Query("SELECT COUNT(t) FROM Task t WHERE t.block.id = :blockId AND t.parentTask.id = :parentTaskId")
    Integer getOrderByBlockIdAndParentTask(@Param("blockId") Long blockId  , @Param("parentTaskId") Long parentTaskId);

    List<Task> findByBlockId(Long blockId);
}
