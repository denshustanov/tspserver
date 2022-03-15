package com.company.tspserver.service.impl;

import com.company.tspserver.entity.User;
import com.company.tspserver.repository.UserRepository;
import com.company.tspserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public User createUser(String username, String password, String bio) {
        return userRepository.createUser(username, password, bio);
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

    @Override
    public boolean checkUsernameAvailable(String username) {
        return userRepository.findUsername(username).isEmpty();
    }

    @Override
    public byte[] getUserAvatar(String username) {
        User user = userRepository.findUserByUsername(username);
        if(user.getAvatar()==null){
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("avatar_placeholder.png");
                if (inputStream != null) {
                    return inputStream.readAllBytes();
                }
                return null;
            } catch (IOException e) {
                return null;
            }
        }
        return userRepository.findUserByUsername(username).getAvatar();
    }
}
