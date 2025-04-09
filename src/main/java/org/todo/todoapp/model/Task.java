package org.todo.todoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDateTime date;

    private LocalDateTime dateNew;

    private LocalDateTime dateMod;

    @NotNull(message = "Priority is mandatory")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String category;

    private String tags;

    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {
    }
}


