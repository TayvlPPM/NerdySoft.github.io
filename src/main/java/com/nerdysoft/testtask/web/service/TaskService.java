package com.nerdysoft.testtask.web.service;


import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.TaskSummary;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.model.UserTask;
import com.nerdysoft.testtask.web.security.UserPrincipal;

import java.util.Set;

public interface TaskService {
    TaskSummary addTask(AddTaskRequest addTaskRequest, UserPrincipal userPrincipal);
    TaskSummary updateTask(Long id, UpdTaskRequest updTaskRequest, UserPrincipal userPrincipal);
    Set<TaskSummary> listAll(UserPrincipal userPrincipal);
    void deleteTask(Long id, UserPrincipal userPrincipal);
    TaskSummary shareTask(Long id, ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal);
}
