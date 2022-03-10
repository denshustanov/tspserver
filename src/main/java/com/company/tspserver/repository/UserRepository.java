package com.company.tspserver.repository;

import com.company.tspserver.entity.User;

import java.util.List;

public interface UserRepository {
    User createUser(String username, String password, String bio, byte[] avatar);

    User updateUserBio(String username, String bio);

    User updateUserAvatar(String username, byte[] avatar);

    User findUserByUsername(String username);

    List<User> findAllUsersByUsernameSubstring(String usernameSubstring);

    void deleteUser(User user);
}
