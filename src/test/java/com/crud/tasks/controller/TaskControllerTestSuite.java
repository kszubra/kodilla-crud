package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    private Gson gson = new Gson();

    @Test
    public void testGetTasks() throws Exception {
        // Given
        Task task1 = new Task(1L, "title", "something");
        Task task2 = new Task(2L, "title2", "something2");
        List<Task> tasks = Arrays.asList(task1, task2);
        TaskDto taskDto1 = new TaskDto(1L, "title", "something");
        TaskDto taskDto2 = new TaskDto(2L, "title2", "something2");
        List<TaskDto> dtos = Arrays.asList(taskDto1, taskDto2);

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(dtos);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].content", is("something")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("title2")))
                .andExpect(jsonPath("$[1].content", is("something2")));
        verify(service, times(1)).getAllTasks();
    }

    @Test
    public void testGetTaskById() throws Exception {
        // Given
        Task task = new Task(1L, "title", "something");
        TaskDto taskDto = new TaskDto(1L, "title", "something");

        when(service.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("something")));
        verify(service, times(1)).getTask(1L);
    }

    @Test
    public void testDeleteTaskById() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .param("taskId", String.valueOf(1L)))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(1L);
    }

    @Test
    public void testCreateTask() throws Exception {
        // Given
        Task task = new Task(1L, "title", "something");
        TaskDto taskDto = new TaskDto(1L, "title", "something");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(taskMapper, times(1)).mapToTask(taskDto);
        verify(service, times(1)).saveTask(task);
    }

    @Test
    public void testUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "title", "something");
        TaskDto taskDto = new TaskDto(1L, "title", "something");

        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("something")));
        verify(taskMapper, times(1)).mapToTask(taskDto);
        verify(taskMapper, times(1)).mapToTaskDto(task);
        verify(service, times(1)).saveTask(task);
    }

}
