package org.todo.todoapp.service;

import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    List<Task> getTasksByUser(Long userId);

    List<Task> getTasksByStatus(TaskStatus status);

    List<Task> getTasksByPriority(Priority priority);

    List<Task> getTasksByDate(LocalDateTime date);

    List<Task> getTasksByCategory(String category);

    List<Task> getTasksByTags(String tags);

    List<Task> getCompletedTasks();

    List<Task> getPendingTasks();

    List<Task> getInProgressTasks();

    List<Task> getCancelledTasks();

    List<Task> getTasksByDateRange(LocalDateTime startDate, LocalDateTime endDate);

}