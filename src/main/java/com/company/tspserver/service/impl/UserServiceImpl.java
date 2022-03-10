package com.company.tspserver.service.impl;

import com.company.tspserver.entity.User;
import com.company.tspserver.repository.UserRepository;
import com.company.tspserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public User createUser(String username, String password, String bio, byte[] avatar) {
        return userRepository.createUser(username, password, bio, avatar);
    }

    @Override
    public User updateUserBio(String username, String bio) {
        return userRepository.updateUserBio(username, bio);
    }

    @Override
    public User updateUserAvatar(String username, byte[] avatar) {
        return userRepository.updateUserAvatar(username, avatar);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> findAllUsersByUsernameSubstring(String usernameSubstring) {
        return userRepository.findAllUsersByUsernameSubstring(usernameSubstring);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
