package org.todo.todoapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.todo.todoapp.model.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("test@email.com");
        testUser.setPassword("password"); // Asumiendo que User tiene un campo password
        userRepository.save(testUser);
    }

    @Test
    void findByUsername_shouldReturnUser() {
        User foundUser = userRepository.findByUsername("testUser");
        assertEquals(testUser.getId(), foundUser.getId());
        assertEquals(testUser.getUsername(), foundUser.getUsername());
    }

    @Test
    void findByUsername_shouldReturnNull() {
        User foundUser = userRepository.findByUsername("nonExistentUser");
        assertNull(foundUser);
    }

    @Test
    void findByEmail_shouldReturnUser() {
        User foundUser = userRepository.findByEmail("test@email.com");
        assertEquals(testUser.getId(), foundUser.getId());
        assertEquals(testUser.getEmail(), foundUser.getEmail());
    }

    @Test
    void findByEmail_shouldReturnNull() {
        User foundUser = userRepository.findByEmail("nonexistent@email.com");
        assertNull(foundUser);
    }

    @Test
    void existsByUsername_shouldReturnTrue() {
        boolean exists = userRepository.existsByUsername("testUser");
        assertTrue(exists);
    }

    @Test
    void existsByUsername_shouldReturnFalse() {
        boolean exists = userRepository.existsByUsername("nonExistentUser");
        assertFalse(exists);
    }

    @Test
    void existsByEmail_shouldReturnTrue() {
        boolean exists = userRepository.existsByEmail("test@email.com");
        assertTrue(exists);
    }

    @Test
    void existsByEmail_shouldReturnFalse() {
        boolean exists = userRepository.existsByEmail("nonexistent@email.com");
        assertFalse(exists);
    }
}