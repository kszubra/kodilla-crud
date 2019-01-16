package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exceptions.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTask(final Long taskId) {return repository.findById(taskId).orElseThrow(()->new TaskNotFoundException("Task with id=" + taskId + " was not found"));}

    public Task saveTask(final Task task) {return repository.save(task);}


}