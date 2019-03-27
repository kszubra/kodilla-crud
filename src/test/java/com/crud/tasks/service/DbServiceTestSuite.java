package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exceptions.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService service;
    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> mockedList = new ArrayList<>();
        mockedList.add(new Task(1L, "title1", "test1"));
        mockedList.add(new Task(2L, "title2", "test2"));
        when(repository.findAll()).thenReturn(mockedList);

        //When
        List<Task> result = service.getAllTasks();

        //Then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(Long.valueOf(1), result.get(0).getId());
        Assert.assertEquals("title1", result.get(0).getTitle());
        Assert.assertEquals("test1", result.get(0).getContent());
        Assert.assertEquals(Long.valueOf(2), result.get(1).getId());
        Assert.assertEquals("title2", result.get(1).getTitle());
        Assert.assertEquals("test2", result.get(1).getContent());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L, "title1", "test1");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(task));

        //When
        Task result1 = service.getTask(1L);

        //Then
        Assert.assertTrue(result1.equals(task));

    }

    @Test(expected = TaskNotFoundException.class)
    public void testGetTaskEmpty() {
        //Given
        when(repository.findById(1L)).thenReturn(java.util.Optional.empty());

        //Then
        Task result1 = service.getTask(1L);

    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "title1", "test1");
        when(repository.save(task)).thenReturn(task);

        //When
        Task result = service.saveTask(task);

        //Then
        Assert.assertEquals(task, result);
    }

}
