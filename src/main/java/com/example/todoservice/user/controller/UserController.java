package com.example.todoservice.user.controller;

import com.example.todoservice.user.entity.User;
import com.example.todoservice.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> create(@RequestBody User user) {
        User save = userService.save(user);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping(path = "/delete/{login}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> delete(@PathVariable("login") UUID login,
                                       @RequestParam("id") UUID id) {
        User user = userService.delete(login, id);
        if (Objects.isNull(user)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
