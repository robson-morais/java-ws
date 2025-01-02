package br.com.rocketseat.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity (name = "tab_users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    // garante qua não haja usernames iguais
    @Column (unique = true)
    private String username;

    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
