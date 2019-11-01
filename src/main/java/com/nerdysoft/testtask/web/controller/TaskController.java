package com.nerdysoft.testtask.web.controller;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.TaskSummary;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.model.UserTask;
import com.nerdysoft.testtask.web.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nerdysoft.testtask.web.security.CurrentUser;
import com.nerdysoft.testtask.web.security.UserPrincipal;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public TaskSummary addNewTask(@Valid @RequestBody AddTaskRequest addTaskRequest, @CurrentUser UserPrincipal currentUser) {
        return taskServiceImpl.addTask(addTaskRequest,currentUser);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskSummary updateMyTask(@PathVariable Long id,@Valid @RequestBody UpdTaskRequest updTaskRequest, @CurrentUser UserPrincipal currentUser) {
        return taskServiceImpl.updateTask(id, updTaskRequest, currentUser);
        }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void removeTask(@PathVariable Long id,@CurrentUser UserPrincipal currentUser) {
        taskServiceImpl.deleteTask( id, currentUser);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Set<UserTask> showTasks(@CurrentUser UserPrincipal currentUser) {
        return taskServiceImpl.listAll(currentUser);

    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public TaskSummary shareTask(@PathVariable Long id, @Valid @RequestBody ShareTaskRequest shareTaskRequest,@CurrentUser UserPrincipal userPrincipal) {
        return taskServiceImpl.shareTask(id, shareTaskRequest, userPrincipal);
    }

}
