package com.nerdysoft.testtask.web.service;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
import com.nerdysoft.testtask.web.dto.TaskSummary;
import com.nerdysoft.testtask.web.dto.UpdTaskRequest;
import com.nerdysoft.testtask.web.exception.NotFoundException;
import com.nerdysoft.testtask.web.model.Task;
import com.nerdysoft.testtask.web.model.User;
import com.nerdysoft.testtask.web.repository.TaskRepository;
import com.nerdysoft.testtask.web.repository.UserRepository;
import com.nerdysoft.testtask.web.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskSummary addTask(AddTaskRequest addTaskRequest, UserPrincipal userPrincipal) {
        User user = userRepository.getOne(userPrincipal.getId());
        Task task = new Task(null,addTaskRequest.getCapture());
        user.getTasks().add(task);
        taskRepository.save(task);
        return TaskSummary.builder()
                .id(task.getId())
                .caption(task.getCapture())
                .sharedBy(task.getSharedBy())
                .build();
    }

    @Override
    public TaskSummary updateTask(Long id ,UpdTaskRequest updTaskRequest ){
        Task updTask = taskRepository.getOne(id);
        updTask.setCapture(updTaskRequest.getNew_caption());
        taskRepository.save(updTask);
        return TaskSummary.builder()
                .id(updTask.getId())
                .caption(updTask.getCapture())
                .sharedBy(updTask.getSharedBy())
                .build();
    }

    @Override
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
            }

    @Override
    public Set<Task> listAll(UserPrincipal userPrincipal){
        User user = userRepository.getOne(userPrincipal.getId());
        return user.getTasks();
    }

    @Override
    public TaskSummary shareTask(Long id, ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal){
        User user = userRepository.findByEmail(shareTaskRequest.getShareToEmail())
                .orElseThrow(() ->
                        new NotFoundException("User not found [email: " + shareTaskRequest.getShareToEmail() + "]")
                );
        Task task = taskRepository.getOne(id);
        task.setSharedBy(userPrincipal.getName());
        user.getTasks().add(task);
        taskRepository.save(task);
        return TaskSummary.builder()
                .id(task.getId())
                .caption(task.getCapture())
                .sharedBy(task.getSharedBy())
                .build();
    }


}
