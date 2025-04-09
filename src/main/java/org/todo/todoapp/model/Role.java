package org.todo.todoapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.security.Permission;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @ElementCollection(targetClass = Permission.class)
    @CollectionTable(name = "roles_permits", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private Set<Permission> permission;

    public Role() {
    }

}