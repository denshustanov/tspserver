package com.company.tspserver.repository;

import com.company.tspserver.entity.User;
import io.jmix.core.entity.KeyValueEntity;

import java.util.List;

public interface UserRepository {
    User createUser(String username, String password, String bio);

    User updateUserBio(String username, String bio);

    User updateUserAvatar(String username, byte[] avatar);

    User findUserByUsername(String username);

    List<User> findAllUsersByUsernameSubstring(String usernameSubstring);

    void deleteUser(User user);

    List<KeyValueEntity> findUsername(String username);
}
