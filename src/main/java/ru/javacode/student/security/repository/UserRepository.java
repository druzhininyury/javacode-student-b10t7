package ru.javacode.student.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.security.model.User;

public interface UserRepository extends JpaRepository<User, String> {



}
