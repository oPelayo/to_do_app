package org.todo.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.todo.todoapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}