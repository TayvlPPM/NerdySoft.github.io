package com.nerdysoft.testtask.web.service;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.TaskSummary;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.exception.NotFoundException;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.model.User;
import com.nerdysoft.testtask.web.model.UserTask;
import com.nerdysoft.testtask.web.model.UserTaskId;
import com.nerdysoft.testtask.web.repository.TaskRepository;
import com.nerdysoft.testtask.web.repository.UserRepository;
import com.nerdysoft.testtask.web.repository.UserTaskRepository;
import com.nerdysoft.testtask.web.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTaskRepository userTaskRepository;

    @Override
    @Transactional
    public TaskSummary addTask(AddTaskRequest addTaskRequest, UserPrincipal userPrincipal) {
        Task task = new Task(addTaskRequest.getCapture());
        User user = userRepository.getOne(userPrincipal.getId());

        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setSharedBy(null);
        userTask.setTask(task);
        userTask.setId(new UserTaskId(user.getId(), task.getId()));
        task.getUserTasks().add(userTask);
        taskRepository.save(task);
        return new TaskSummary(task.getId(),
                task.getCapture(),
                userTask.getSharedBy());

    }

    @Override
    @Transactional
    public TaskSummary updateTask(Long id, UpdTaskRequest updTaskRequest, UserPrincipal userPrincipal) {
        Task task = taskRepository.getOne(id);
        task.setCapture(updTaskRequest.getNew_caption());
        UserTask userTask = userTaskRepository.findByUser_idAndTask_id(userPrincipal.getId(),id);
        taskRepository.save(task);
        return new TaskSummary(task.getId(),
                task.getCapture(),
                userTask.getSharedBy());

    }

    @Override
    @Transactional
    public void deleteTask(Long id, UserPrincipal userPrincipal) {
        userTaskRepository.delete(userTaskRepository.findByUser_idAndTask_id(userPrincipal.getId(), id));
        if (userTaskRepository.findByTask_id(id).isEmpty()) taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Set<TaskSummary> listAll(UserPrincipal userPrincipal) {
        User user = userRepository.getOne(userPrincipal.getId());
        Task task = new Task();
        Set<UserTask> connections = user.getUserTasks();
        Set<TaskSummary> taskList = new HashSet<>();

        for (UserTask userTask : connections) {
            task = userTask.getTask();
            TaskSummary taskSummary = new TaskSummary(task.getId(),task.getCapture(),userTask.getSharedBy());
            taskList.add(taskSummary);
        }

        return taskList;
    }

    @Override
    @Transactional
    public TaskSummary shareTask(Long id, ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal) {
        User user = userRepository.findByEmail(shareTaskRequest.getShareToEmail())
                .orElseThrow(() ->
                        new NotFoundException("User not found [email: " + shareTaskRequest.getShareToEmail() + "]")
                );
        Task task = taskRepository.getOne(id);

        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setSharedBy(userPrincipal.getName());
        userTask.setTask(task);
        userTask.setId(new UserTaskId(user.getId(), task.getId()));
        task.getUserTasks().add(userTask);
        return new TaskSummary(task.getId(),
                task.getCapture(),
                userTask.getSharedBy());

    }


}



