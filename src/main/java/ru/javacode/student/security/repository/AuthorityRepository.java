package ru.javacode.student.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacode.student.security.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {



}
