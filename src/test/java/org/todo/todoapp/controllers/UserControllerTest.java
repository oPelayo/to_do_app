package org.todo.todoapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.todo.todoapp.exceptions.UserNotFoundException;
import org.todo.todoapp.model.User;
import org.todo.todoapp.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

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
        when(userService.createUser(testUser)).thenReturn(testUser);
        ResponseEntity<User> response = userController.createUser(testUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void getUserById_shouldReturnUser() {
        when(userService.getUserById(1L)).thenReturn(testUser);
        ResponseEntity<User> response = userController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void getUserById_shouldReturnNotFound() {
        when(userService.getUserById(1L)).thenThrow(new UserNotFoundException("User not found"));
        ResponseEntity<User> response = userController.getUserById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getUserByUsername_shouldReturnUser() {
        when(userService.getUserByUsername("testUser")).thenReturn(testUser);
        ResponseEntity<User> response = userController.getUserByUsername("testUser");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void getUserByUsername_shouldReturnNotFound() {
        when(userService.getUserByUsername("testUser")).thenThrow(new UserNotFoundException("User not found"));
        ResponseEntity<User> response = userController.getUserByUsername("testUser");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getUserByEmail_shouldReturnUser() {
        when(userService.getUserByEmail("test@email.com")).thenReturn(testUser);
        ResponseEntity<User> response = userController.getUserByEmail("test@email.com");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void getUserByEmail_shouldReturnNotFound() {
        when(userService.getUserByEmail("test@email.com")).thenThrow(new UserNotFoundException("User not found"));
        ResponseEntity<User> response = userController.getUserByEmail("test@email.com");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        List<User> users = Arrays.asList(testUser, new User());
        when(userService.getAllUsers()).thenReturn(users);
        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() {
        when(userService.updateUser(1L, testUser)).thenReturn(testUser);
        ResponseEntity<User> response = userController.updateUser(1L, testUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void updateUser_shouldReturnNotFound() {
        when(userService.updateUser(1L, testUser)).thenThrow(new UserNotFoundException("User not found"));
        ResponseEntity<User> response = userController.updateUser(1L, testUser);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNoContent() {
        doNothing().when(userService).deleteUser(1L);
        ResponseEntity<Void> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteUser_shouldReturnNotFound() {
        doThrow(new UserNotFoundException("User not found")).when(userService).deleteUser(1L);
        ResponseEntity<Void> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}