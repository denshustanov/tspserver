package com.company.tspserver.controller;

import com.company.tspserver.dto.UserDTO;
import com.company.tspserver.entity.User;
import com.company.tspserver.service.PostService;
import com.company.tspserver.service.SubscriptionService;
import com.company.tspserver.service.UserService;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected SubscriptionService subscriptionService;

    @Autowired
    protected CurrentAuthentication currentAuthentication;

    @Autowired
    protected PostService postService;

    @PostMapping(value = "/user/register")
    public ResponseEntity registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getBio());

        return ResponseEntity.ok(new UserDTO((user)));
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity getUser(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        UserDTO userDTO = new UserDTO(user);
        userDTO.setSubscribersCount(subscriptionService.calculateUserSubscribers(username));
        userDTO.setSubscriptionsCount(subscriptionService.calculateUserSubscriptions(username));
        userDTO.setPostsCount(postService.calculateUserPosts(username));
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(value = "/user/{username}/avatar")
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody byte[] avatar){
        if(!currentAuthentication.getUser().getUsername().equals(username)){
            return ResponseEntity.status(403).body("Can not update another user!");
        }

        userService.updateUserAvatar(username, avatar);
        return ResponseEntity.ok(new UserDTO(userService.findUserByUsername(username)));
    }

    @PutMapping(value = "/user/{username}/bio")
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody String bio){
        if(!currentAuthentication.getUser().getUsername().equals(username)){
            return ResponseEntity.status(403).body("Can not update another user!");
        }

        userService.updateUserBio(username, bio);
        return ResponseEntity.ok(new UserDTO(userService.findUserByUsername(username)));
    }

    @GetMapping(value = "/user/{username}/available")
    public ResponseEntity getUsernameAvailable(@PathVariable String username){
        return ResponseEntity.ok(userService.checkUsernameAvailable(username));
    }

    @GetMapping(value = "/user/{username}/avatar")
    public ResponseEntity getUserAvatar(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserAvatar(username));
    }
}
