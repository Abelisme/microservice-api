package com.example.will.controller;

import com.example.will.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping(value = "/{id}")
    @ResponseBody
    public String getUser(@PathVariable String id) {
        // 模擬從數據庫獲取用戶
        Long userId = Long.parseLong(id);
        User user = new User(userId, "User " + userId);
        // Convert User object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        System.out.println("User: " + user);
        try {
            jsonString = objectMapper.writeValueAsString(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    @GetMapping("/msg")
    public String test() {
        return "Hello this is my second microservice";
    }
}

