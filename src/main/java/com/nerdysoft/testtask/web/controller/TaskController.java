package com.nerdysoft.testtask.web.controller;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nerdysoft.testtask.web.security.CurrentUser;
import com.nerdysoft.testtask.web.security.UserPrincipal;

@RestController
@RequestMapping("/api/users")
public class TaskController {

    private TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public String addNewTask(AddTaskRequest addTaskRequest, @CurrentUser UserPrincipal currentUser) {
        taskServiceImpl.addTask(addTaskRequest,currentUser);
        return "aded";
    }

}
