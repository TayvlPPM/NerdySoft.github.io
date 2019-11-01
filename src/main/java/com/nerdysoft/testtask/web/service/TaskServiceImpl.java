package com.nerdysoft.testtask.web.service;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.TaskSummary;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.exception.NotFoundException;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.model.User;
import com.nerdysoft.testtask.web.model.UserTask;
import com.nerdysoft.testtask.web.repository.TaskRepository;
import com.nerdysoft.testtask.web.repository.UserRepository;
import com.nerdysoft.testtask.web.repository.UserTaskRepository;
import com.nerdysoft.testtask.web.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TaskSummary addTask(AddTaskRequest addTaskRequest, UserPrincipal userPrincipal) {
        Task task = new Task(addTaskRequest.getCapture());
        User user = userRepository.getOne(userPrincipal.getId());
        taskRepository.save(task);
        UserTask userTask = new UserTask();
        userTask.setTask(task);
        userTask.setUser(user);
        userTask.setSharedBy(null);

        userTaskRepository.save(userTask);


        TaskSummary taskSummary = new TaskSummary(task.getId(),
                task.getCapture(),
                userTask.getSharedBy());
        return taskSummary;

    }

    @Override
    public TaskSummary updateTask(Long id, UpdTaskRequest updTaskRequest, UserPrincipal userPrincipal) {
        Task updTask = taskRepository.getOne(id);
        updTask.setCapture(updTaskRequest.getNew_caption());
        taskRepository.save(updTask);
        UserTask userTask = new UserTask();
        userTaskRepository.findByUser_id(userPrincipal.getId());
        TaskSummary taskSummary = new TaskSummary(updTask.getId(),
                updTask.getCapture(),
                userTask.getSharedBy());
        return taskSummary;
    }

    @Override
    public void deleteTask(Long id, UserPrincipal userPrincipal) {
        userTaskRepository.delete(userTaskRepository.findByUser_idAndTask_id(userPrincipal.getId(), id));
        if (userTaskRepository.findByTask_id(id).isEmpty()) taskRepository.deleteById(id);
    }

    @Override
    public Set<Task> listAll(UserPrincipal userPrincipal) {
        User user = userRepository.getOne(userPrincipal.getId());
        Set<UserTask> connections = user.getUserTasks();
        Set<Task> taskList = new HashSet<>();
        Iterator<UserTask> iter = connections.iterator();

        while (iter.hasNext()) {
            taskList.add(iter.next().getTask());
        }
        return taskList;
    }

    @Override
    public void shareTask(Long id, ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal) {
        User user = userRepository.findByEmail(shareTaskRequest.getShareToEmail())
                .orElseThrow(() ->
                        new NotFoundException("User not found [email: " + shareTaskRequest.getShareToEmail() + "]")
                );
        Task task = taskRepository.getOne(id);
        UserTask userTask = new UserTask();
        userTask.setTask(task);
        userTask.setUser(user);
        userTask.setSharedBy(userPrincipal.getName());
        user.getUserTasks().add(userTask);
        taskRepository.save(task);

    }

}



