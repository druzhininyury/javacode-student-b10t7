package ru.javacode.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public String testUser() {
        return "Test endpoint for authorized user.";
    }

    @GetMapping("/moderator")
    @ResponseStatus(HttpStatus.OK)
    public String testModerator() {
        return "Test endpoint for authorized moderator.";
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public String testAdmin() {
        return "Test endpoint for authorized admin.";
    }

}
