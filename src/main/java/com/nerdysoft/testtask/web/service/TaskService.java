package com.nerdysoft.testtask.web.service;


import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.DeleteTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.security.UserPrincipal;

import java.util.Set;

public interface TaskService {
    void addTask(AddTaskRequest addTaskRequest, UserPrincipal userPrincipal);
    void updateTask(UpdTaskRequest updTaskRequest );
    Set<Task> listAll(UserPrincipal userPrincipal);
    void deleteTask(DeleteTaskRequest deleteTaskRequest);
    void shareTask(ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal);
}
