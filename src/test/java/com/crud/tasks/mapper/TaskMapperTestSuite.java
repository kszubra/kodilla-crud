package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "abc", "sth");

        //When
        Task result = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(Long.valueOf(1), result.getId());
        assertEquals("abc", result.getTitle());
        assertEquals("sth", result.getContent());

    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "abc", "sth");

        //When
        TaskDto result = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(Long.valueOf(1), result.getId());
        assertEquals("abc", result.getTitle());
        assertEquals("sth", result.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks;
        Task task1 = new Task(1L, "abc", "sth1");
        Task task2 = new Task(2L, "def", "sth2");
        tasks = Arrays.asList(task1, task2);

        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(2, result.size());
        assertEquals(Long.valueOf(1), result.get(0).getId());
        assertEquals(Long.valueOf(2), result.get(1).getId());
        assertEquals("abc", result.get(0).getTitle());
        assertEquals("def", result.get(1).getTitle());
        assertEquals("sth1", result.get(0).getContent());
        assertEquals("sth2", result.get(1).getContent());

    }
}
