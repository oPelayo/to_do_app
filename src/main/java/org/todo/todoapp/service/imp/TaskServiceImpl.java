package org.todo.todoapp.service.imp;

import org.springframework.stereotype.Service;
import org.todo.todoapp.exceptions.TaskNotFoundException;
import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;
import org.todo.todoapp.repository.TaskRepository;
import org.todo.todoapp.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with ID: " + id + " not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with ID: " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUser_Id(userId);
    }


    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> getTasksByDate(LocalDateTime date) {
        return taskRepository.findByDate(date);
    }

    @Override
    public List<Task> getTasksByCategory(String category) {
        return taskRepository.findByCategory(category);
    }

    @Override
    public List<Task> getTasksByTags(String tags) {
        return taskRepository.findByTags(tags);
    }

    @Override
    public List<Task> getCompletedTasks() {
        return taskRepository.findByStatus(TaskStatus.COMPLETED);
    }

    @Override
    public List<Task> getPendingTasks() {
        return taskRepository.findByStatus(TaskStatus.PENDING);
    }

    @Override
    public List<Task> getInProgressTasks() {
        return taskRepository.findByStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public List<Task> getCancelledTasks() {
        return taskRepository.findByStatus(TaskStatus.CANCELED);
    }

    @Override
    public List<Task> getTasksByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return taskRepository.findByDateBetween(startDate, endDate);
    }
}