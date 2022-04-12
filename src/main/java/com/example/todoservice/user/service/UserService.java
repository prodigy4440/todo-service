package com.example.todoservice.user.service;

import com.example.todoservice.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private Map<UUID, User> userMap = new HashMap<>();

    public User save(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    public User delete(UUID login, UUID id) {
        if (Objects.equals(login, id)) {
            User user = userMap.get(login);
            if (Objects.isNull(user)) {
                return null;
            }
            return userMap.remove(login);
        }

        User loginUser = userMap.get(login);
        if (loginUser.getType() != User.Type.ADMIN) {
            return null;
        }
        User user = userMap.get(id);
        if (Objects.isNull(user)) {
            return null;
        }
        return userMap.remove(id);
    }

    public List<User> list(UUID login) {
        User user = userMap.get(login);
        if (Objects.isNull(user) || (user.getType() != User.Type.ADMIN)) {
            return new LinkedList<>();
        }
        return userMap.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
    }
}
