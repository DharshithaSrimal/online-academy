package com.online_academy.user_service.service;

import com.online_academy.user_service.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
