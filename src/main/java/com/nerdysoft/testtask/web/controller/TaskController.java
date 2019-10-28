package com.nerdysoft.testtask.web.controller;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.DeleteTaskRequest;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nerdysoft.testtask.web.security.CurrentUser;
import com.nerdysoft.testtask.web.security.UserPrincipal;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class TaskController {

    private TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public String addNewTask(@Valid @RequestBody AddTaskRequest addTaskRequest, @CurrentUser UserPrincipal currentUser) {
        taskServiceImpl.addTask(addTaskRequest,currentUser);
        return "aded";
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public String updateMyTask(@Valid @RequestBody UpdTaskRequest updTaskRequest, @CurrentUser UserPrincipal currentUser) {
        taskServiceImpl.updateTask(updTaskRequest);
        return "updated";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public String removeTask(@Valid @RequestBody DeleteTaskRequest deleteTaskRequest) {
        taskServiceImpl.deleteTask(deleteTaskRequest);
        return "deleted";
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public Set<Task> showTask(@CurrentUser UserPrincipal currentUser) {
        return taskServiceImpl.listAll(currentUser);

    }

}
