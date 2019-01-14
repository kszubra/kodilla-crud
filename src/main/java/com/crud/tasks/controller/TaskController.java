package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // Adnotacją oznaczamy klasę, którą chcemy "wystawić" dla świata — za jej pomocą tworzymy wejście do aplikacji z zewnętrznego świata
@RequestMapping("/v1/task") // Dzięki tej operacji, nasz RestController będzie dostępny pod poniższym adresem: http://localhost:8080/v1/task
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks") // Dzięki operacjom, które przed chwilą wykonaliśmy, URL do pobrania wszystkich zadań to: http://localhost:8080/v1/task/getTasks
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    public void deleteTask(Long taskId) {

    }

    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "edited test title", "test content");
    }

    public void createTask(TaskDto taskDto) {

    }


}
