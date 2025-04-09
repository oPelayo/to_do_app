package org.todo.todoapp.service.imp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.todo.todoapp.exceptions.UserNotFoundException;
import org.todo.todoapp.model.User;
import org.todo.todoapp.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");
        testUser.setEmail("test@email.com");
    }

    @Test
    void createUser_shouldReturnCreatedUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);
        User createdUser = userService.createUser(testUser);
        assertEquals(testUser, createdUser);
    }

    @Test
    void getUserById_shouldReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        User foundUser = userService.getUserById(1L);
        assertEquals(testUser, foundUser);
    }

    @Test
    void getUserById_shouldThrowUserNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void getUserByUsername_shouldReturnUser() {
        when(userRepository.findByUsername("testUser")).thenReturn(testUser);
        User foundUser = userService.getUserByUsername("testUser");
        assertEquals(testUser, foundUser);
    }

    @Test
    void getUserByUsername_shouldThrowUserNotFoundException() {
        when(userRepository.findByUsername("testUser")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUserByUsername("testUser"));
    }

    @Test
    void getUserByEmail_shouldReturnUser() {
        when(userRepository.findByEmail("test@email.com")).thenReturn(testUser);
        User foundUser = userService.getUserByEmail("test@email.com");
        assertEquals(testUser, foundUser);
    }

    @Test
    void getUserByEmail_shouldThrowUserNotFoundException() {
        when(userRepository.findByEmail("test@email.com")).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("test@email.com"));
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        List<User> users = Arrays.asList(testUser, new User());
        when(userRepository.findAll()).thenReturn(users);
        List<User> foundUsers = userService.getAllUsers();
        assertEquals(users, foundUsers);
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(testUser)).thenReturn(testUser);
        User updatedUser = userService.updateUser(1L, testUser);
        assertEquals(testUser, updatedUser);
    }

    @Test
    void updateUser_shouldThrowUserNotFoundException() {
        when(userRepository.existsById(1L)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, testUser));
    }

    @Test
    void deleteUser_shouldDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_shouldThrowUserNotFoundException() {
        when(userRepository.existsById(1L)).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
    }

    @Test
    void existsByUsername_shouldReturnTrue() {
        when(userRepository.existsByUsername("testUser")).thenReturn(true);
        assertTrue(userService.existsByUsername("testUser"));
    }

    @Test
    void existsByUsername_shouldReturnFalse() {
        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        assertFalse(userService.existsByUsername("testUser"));
    }

    @Test
    void existsByEmail_shouldReturnTrue() {
        when(userRepository.existsByEmail("test@email.com")).thenReturn(true);
        assertTrue(userService.existsByEmail("test@email.com"));
    }

    @Test
    void existsByEmail_shouldReturnFalse() {
        when(userRepository.existsByEmail("test@email.com")).thenReturn(false);
        assertFalse(userService.existsByEmail("test@email.com"));
    }
}