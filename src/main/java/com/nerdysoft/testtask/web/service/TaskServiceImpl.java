package com.nerdysoft.testtask.web.service;

import com.nerdysoft.testtask.web.dto.AddTaskRequest;
import com.nerdysoft.testtask.web.dto.DeleteTaskRequest;
import com.nerdysoft.testtask.web.dto.ShareTaskRequest;
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
    public void addTask(AddTaskRequest addTaskRequest, UserPrincipal userPrincipal) {
        User user = userRepository.getOne(userPrincipal.getId());
        Task task = new Task(null,addTaskRequest.getCapture());
        user.getTasks().add(task);
        user.getUsers().add(user);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(UpdTaskRequest updTaskRequest ){
        Task updTask = taskRepository.getOne(updTaskRequest.getId());
        updTask.setCapture(updTaskRequest.getNew_caption());
        taskRepository.save(updTask);
    }

    @Override
    public void deleteTask(DeleteTaskRequest deleteTaskRequest){
        taskRepository.deleteById(deleteTaskRequest.getId());
            }

    @Override
    public Set<Task> listAll(UserPrincipal userPrincipal){
        User user = userRepository.getOne(userPrincipal.getId());
        return user.getTasks();
    }

    @Override
    public void shareTask(ShareTaskRequest shareTaskRequest, UserPrincipal userPrincipal){
        User user = userRepository.findByEmail(shareTaskRequest.getShareToEmail())
                .orElseThrow(() ->
                        new NotFoundException("User not found [email: " + shareTaskRequest.getShareToEmail() + "]")
                );
        Task task = taskRepository.getOne(shareTaskRequest.getId());
        task.setSharedBy(userPrincipal.getName());
        user.getTasks().add(task);
        task.getUsers().add(user);
        taskRepository.save(task);
    }


}
