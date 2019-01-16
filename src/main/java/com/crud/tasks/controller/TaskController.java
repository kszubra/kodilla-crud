package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController // Adnotacją oznaczamy klasę, którą chcemy "wystawić" dla świata — za jej pomocą tworzymy wejście do aplikacji z zewnętrznego świata
@RequestMapping("/v1/task") // Dzięki tej operacji, nasz RestController będzie dostępny pod poniższym adresem: http://localhost:8080/v1/task
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks") // Dzięki operacjom, które przed chwilą wykonaliśmy, URL do pobrania wszystkich zadań to: http://localhost:8080/v1/task/getTasks
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @GetMapping("getTask/{id}")
    public TaskDto getTask(@PathVariable("id") Long taskId) {
        return taskMapper.mapToTaskDto(service.getTask(taskId));
    }

    @DeleteMapping("deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
        service.deleteTask(taskId);
    }

    @PutMapping("update")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @PostMapping(value = "createTask", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }


}
