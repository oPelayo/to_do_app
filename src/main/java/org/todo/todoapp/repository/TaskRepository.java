package org.todo.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todo.todoapp.model.Priority;
import org.todo.todoapp.model.Task;
import org.todo.todoapp.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long userId);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(Priority priority);

    List<Task> findByDate(LocalDateTime date);

    List<Task> findByCategory(String category);

    List<Task> findByTags(String tags);

    List<Task> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}