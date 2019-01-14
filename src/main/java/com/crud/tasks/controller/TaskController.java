package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // Adnotacją oznaczamy klasę, którą chcemy "wystawić" dla świata — za jej pomocą tworzymy wejście do aplikacji z zewnętrznego świata
@RequestMapping("/v1/task") // Dzięki tej operacji, nasz RestController będzie dostępny pod poniższym adresem: http://localhost:8080/v1/task
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks") // Dzięki operacjom, które przed chwilą wykonaliśmy, URL do pobrania wszystkich zadań to: http://localhost:8080/v1/task/getTasks
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping("getTask/{id}")
    public TaskDto getTask(@PathVariable("id") Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping("delete/{id}")
    public void deleteTask(@PathVariable("id") Long taskId) {

    }

    @PutMapping("update")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return new TaskDto(1L, "edited test title", "test content");
    }

    @PostMapping("create")
    public void createTask(@RequestBody TaskDto taskDto) {

    }


}
