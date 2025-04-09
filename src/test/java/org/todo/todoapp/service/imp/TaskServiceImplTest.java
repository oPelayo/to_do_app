package org.todo.todoapp.service.imp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.todo.todoapp.exceptions.TaskNotFoundException;
import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;
import org.todo.todoapp.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task testTask;

    @BeforeEach
    void setUp() {
        testTask = new Task();
        testTask.setId(1L);
        testTask.setName("Test Task");
        testTask.setStatus(TaskStatus.PENDING);
    }

    @Test
    void createTask_shouldReturnCreatedTask() {
        when(taskRepository.save(testTask)).thenReturn(testTask);
        Task createdTask = taskService.createTask(testTask);
        assertEquals(testTask, createdTask);
    }

    @Test
    void getTaskById_shouldReturnTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        Task foundTask = taskService.getTaskById(1L);
        assertEquals(testTask, foundTask);
    }

    @Test
    void getTaskById_shouldThrowTaskNotFoundException() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    void getAllTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Arrays.asList(testTask, new Task());
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> foundTasks = taskService.getAllTasks();
        assertEquals(tasks, foundTasks);
    }

    @Test
    void updateTask_shouldReturnUpdatedTask() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        when(taskRepository.save(testTask)).thenReturn(testTask);
        Task updatedTask = taskService.updateTask(1L, testTask);
        assertEquals(testTask, updatedTask);
    }

    @Test
    void updateTask_shouldThrowTaskNotFoundException() {
        when(taskRepository.existsById(1L)).thenReturn(false);
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(1L, testTask));
    }

    @Test
    void deleteTask_shouldDeleteTask() {
        when(taskRepository.existsById(1L)).thenReturn(true);
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteTask_shouldThrowTaskNotFoundException() {
        when(taskRepository.existsById(1L)).thenReturn(false);
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1L));
    }

    @Test
    void getTasksByUser_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByUser_Id(1L)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByUser(1L);
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getTasksByStatus_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByStatus(TaskStatus.PENDING)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByStatus(TaskStatus.PENDING);
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getTasksByPriority_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByPriority(Priority.HIGH)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByPriority(Priority.HIGH);
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getTasksByDate_shouldReturnListOfTasks() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByDate(now)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByDate(now);
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getTasksByCategory_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByCategory("Category")).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByCategory("Category");
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getTasksByTags_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByTags("Tags")).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByTags("Tags");
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getCompletedTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByStatus(TaskStatus.COMPLETED)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getCompletedTasks();
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getPendingTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByStatus(TaskStatus.PENDING)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getPendingTasks();
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getInProgressTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByStatus(TaskStatus.IN_PROGRESS)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getInProgressTasks();
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getCancelledTasks_shouldReturnListOfTasks() {
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByStatus(TaskStatus.CANCELED)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getCancelledTasks();
        assertEquals(tasks, foundTasks);
    }

    @Test
    void getTasksByDateRange_shouldReturnListOfTasks() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);
        List<Task> tasks = Collections.singletonList(testTask);
        when(taskRepository.findByDateBetween(start, end)).thenReturn(tasks);
        List<Task> foundTasks = taskService.getTasksByDateRange(start, end);
        assertEquals(tasks, foundTasks);
    }
}