package br.com.rocketseat.todolist;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity (name = "tab_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;
    private String username;
    private String password;
}
