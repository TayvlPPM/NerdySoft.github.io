package com.nerdysoft.testtask.web.controller;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.TaskSummary;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public TaskSummary updateMyTask(@PathVariable Long id,@Valid @RequestBody UpdTaskRequest updTaskRequest) {
        return taskServiceImpl.updateTask(id, updTaskRequest);
        }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void removeTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Set<Task> showTasks(@CurrentUser UserPrincipal currentUser) {
        return taskServiceImpl.listAll(currentUser);

    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public TaskSummary shareTask(@PathVariable Long id, @Valid @RequestBody ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal) {
        return taskServiceImpl.shareTask(id, shareTaskRequest, userPrincipal);
    }

}
