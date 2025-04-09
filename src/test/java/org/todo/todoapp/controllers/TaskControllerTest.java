package org.todo.todoapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.todo.todoapp.exceptions.TaskNotFoundException;
import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;
import org.todo.todoapp.service.TaskService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private Task testTask;
    private LocalDateTime testDate;

    @BeforeEach
    void setUp() {
        testTask = new Task();
        testTask.setId(1L);
        testTask.setName("Test Task");
        testTask.setStatus(TaskStatus.PENDING);
        testDate = LocalDateTime.now();
    }

    @Test
    void createTask_shouldReturnCreatedTask() {
        when(taskService.createTask(testTask)).thenReturn(testTask);
        ResponseEntity<Task> response = taskController.createTask(testTask);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testTask, response.getBody());
    }

    @Test
    void getTaskById_shouldReturnTask() {
        when(taskService.getTaskById(1L)).thenReturn(testTask);
        ResponseEntity<Task> response = taskController.getTaskById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testTask, response.getBody());
    }

    @Test
    void getTaskById_shouldReturnNotFound() {
        when(taskService.getTaskById(1L)).thenThrow(new TaskNotFoundException("Task not found"));
        ResponseEntity<Task> response = taskController.getTaskById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Arrays.asList(testTask, new Task());
        when(taskService.getAllTasks()).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void updateTask_shouldReturnUpdatedTask() {
        when(taskService.updateTask(1L, testTask)).thenReturn(testTask);
        ResponseEntity<Task> response = taskController.updateTask(1L, testTask);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testTask, response.getBody());
    }

    @Test
    void updateTask_shouldReturnNotFound() {
        when(taskService.updateTask(1L, testTask)).thenThrow(new TaskNotFoundException("Task not found"));
        ResponseEntity<Task> response = taskController.updateTask(1L, testTask);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteTask_shouldReturnNoContent() {
        doNothing().when(taskService).deleteTask(1L);
        ResponseEntity<Void> response = taskController.deleteTask(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteTask_shouldReturnNotFound() {
        doThrow(new TaskNotFoundException("Task not found")).when(taskService).deleteTask(1L);
        ResponseEntity<Void> response = taskController.deleteTask(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getTasksByUser_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByUser(1L)).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getTasksByStatus_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByStatus(TaskStatus.PENDING)).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByStatus(TaskStatus.PENDING);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getTasksByPriority_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByPriority(Priority.HIGH)).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByPriority(Priority.HIGH);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getTasksByDate_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByDate(testDate)).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByDate(testDate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getTasksByCategory_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByCategory("Category")).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByCategory("Category");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getTasksByTags_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByTags("Tags")).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByTags("Tags");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getCompletedTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getCompletedTasks()).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getCompletedTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getPendingTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getPendingTasks()).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getPendingTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getInProgressTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getInProgressTasks()).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getInProgressTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getCancelledTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getCancelledTasks()).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getCancelledTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    void getTasksByDateRange_shouldReturnListOfTasks() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskService.getTasksByDateRange(start, end)).thenReturn(tasks);
        ResponseEntity<List<Task>> response = taskController.getTasksByDateRange(start, end);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }
}
