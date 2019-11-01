package com.nerdysoft.testtask.web.repository;

import com.nerdysoft.testtask.web.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

    Set<UserTask> findByUser_id(Long user_id);
    Set<UserTask> findByTask_id(Long user_id);
    UserTask findByUser_idAndTask_id(Long user_id, Long task_id);
}