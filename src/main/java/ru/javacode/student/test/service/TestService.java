package ru.javacode.student.test.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    //@PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @Secured({"MODERATOR", "ADMIN"})
    public String privilegedUsersOnlyMethod() {
        return "Privileged users only method!";
    }

}
