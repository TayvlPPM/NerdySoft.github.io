package com.nerdysoft.testtask.web.repository;

import com.nerdysoft.testtask.web.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
