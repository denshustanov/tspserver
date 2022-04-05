package com.company.tspserver.service;

import com.company.tspserver.entity.User;

import java.util.List;

public interface UserService {
    User createUser(String username, String password, String bio);

    User updateUserBio(String username, String bio);

    User updateUserAvatar(String username, byte[] avatar);

    User findUserByUsername(String username);

    List<User> findAllUsersByUsernameSubstring(String usernameSubstring);

    void deleteUser(User user);

    boolean checkUsernameAvailable(String username);

    byte[] getUserAvatar(String username);
}
