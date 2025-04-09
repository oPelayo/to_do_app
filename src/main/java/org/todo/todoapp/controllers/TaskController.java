package org.todo.todoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.todoapp.exceptions.TaskNotFoundException;
import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;
import org.todo.todoapp.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (TaskNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (TaskNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(taskService.getTasksByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        return new ResponseEntity<>(taskService.getTasksByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Priority priority) {
        return new ResponseEntity<>(taskService.getTasksByPriority(priority), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Task>> getTasksByDate(@PathVariable LocalDateTime date) {
        return new ResponseEntity<>(taskService.getTasksByDate(date), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable String category) {
        return new ResponseEntity<>(taskService.getTasksByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/tags/{tags}")
    public ResponseEntity<List<Task>> getTasksByTags(@PathVariable String tags) {
        return new ResponseEntity<>(taskService.getTasksByTags(tags), HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        return new ResponseEntity<>(taskService.getCompletedTasks(), HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        return new ResponseEntity<>(taskService.getPendingTasks(), HttpStatus.OK);
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<Task>> getInProgressTasks() {
        return new ResponseEntity<>(taskService.getInProgressTasks(), HttpStatus.OK);
    }

    @GetMapping("/cancelled")
    public ResponseEntity<List<Task>> getCancelledTasks() {
        return new ResponseEntity<>(taskService.getCancelledTasks(), HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Task>> getTasksByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(taskService.getTasksByDateRange(startDate, endDate), HttpStatus.OK);
    }
}