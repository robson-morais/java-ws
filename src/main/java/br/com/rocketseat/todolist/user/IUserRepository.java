package br.com.rocketseat.todolist.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository <User, UUID> {

    User findUserByUsername (String username);

}
