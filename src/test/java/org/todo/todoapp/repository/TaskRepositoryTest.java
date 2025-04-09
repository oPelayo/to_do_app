package org.todo.todoapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;
import org.todo.todoapp.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private Task testTask;
    private User testUser;
    private LocalDateTime testDate;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("test@email.com");
        testUser.setPassword("password");
        testUser = userRepository.save(testUser);

        testTask = new Task();
        testTask.setName("Test Task");
        testTask.setDescription("Test Description");
        testTask.setStatus(TaskStatus.PENDING);
        testTask.setPriority(Priority.HIGH);
        testTask.setCategory("Test Category");
        testTask.setTags("Test Tags");
        testTask.setUser(testUser);
        testDate = LocalDateTime.now();
        testTask.setDate(testDate);
        taskRepository.save(testTask);
    }

    @Test
    void findByUser_Id_shouldReturnListOfTasks() {
        List<Task> tasks = taskRepository.findByUser_Id(testUser.getId());
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByStatus_shouldReturnListOfTasks() {
        List<Task> tasks = taskRepository.findByStatus(TaskStatus.PENDING);
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByPriority_shouldReturnListOfTasks() {
        List<Task> tasks = taskRepository.findByPriority(Priority.HIGH);
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByDate_shouldReturnListOfTasks() {
        List<Task> tasks = taskRepository.findByDate(testDate);
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByCategory_shouldReturnListOfTasks() {
        List<Task> tasks = taskRepository.findByCategory("Test Category");
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByTags_shouldReturnListOfTasks() {
        List<Task> tasks = taskRepository.findByTags("Test Tags");
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByDateBetween_shouldReturnListOfTasks() {
        LocalDateTime startDate = testDate.minusDays(1);
        LocalDateTime endDate = testDate.plusDays(1);
        List<Task> tasks = taskRepository.findByDateBetween(startDate, endDate);
        assertEquals(1, tasks.size());
        assertEquals(testTask.getId(), tasks.getFirst().getId());
    }

    @Test
    void findByDateBetween_shouldReturnEmptyList() {
        LocalDateTime startDate = testDate.plusDays(2);
        LocalDateTime endDate = testDate.plusDays(3);
        List<Task> tasks = taskRepository.findByDateBetween(startDate, endDate);
        assertTrue(tasks.isEmpty());
    }
}