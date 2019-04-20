package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController // Adnotacją oznaczamy klasę, którą chcemy "wystawić" dla świata — za jej pomocą tworzymy wejście do aplikacji z zewnętrznego świata
@RequestMapping("/v1") // Dzięki tej operacji, nasz RestController będzie dostępny pod poniższym adresem: http://localhost:8080/v1/task
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "tasks") // Dzięki operacjom, które przed chwilą wykonaliśmy, URL do pobrania wszystkich zadań to: http://localhost:8080/v1/task/getTasks
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping("tasks/{id}")
    public TaskDto getTask(@PathVariable("id") Long taskId) {
        return taskMapper.mapToTaskDto(service.getTask(taskId));
    }

    @DeleteMapping("tasks/{id}")
    public void deleteTask(@PathVariable("id") Long taskId) {
        service.deleteTask(taskId);
    }

    @PutMapping("tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping(value = "tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }


}
