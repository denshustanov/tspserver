package com.company.tspserver.controller;

import com.company.tspserver.dto.UserDTO;
import com.company.tspserver.entity.User;
import com.company.tspserver.service.SubscriptionService;
import com.company.tspserver.service.UserService;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import liquibase.pro.packaged.U;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected SubscriptionService subscriptionService;

    @Autowired
    protected CurrentAuthentication currentAuthentication;


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/user/register")
    public ResponseEntity registerUser(@RequestBody UserDTO userDTO) {
        logger.info("register " + currentAuthentication.getUser().getUsername());
        byte[] avatar = null;
        if (userDTO.getAvatar()!=null){
            avatar = Base64.getDecoder().decode(userDTO.getAvatar());
        }
        User user = userService.createUser(userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getBio(),
                avatar);

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity getUser(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        UserDTO userDTO = new UserDTO(user);
        userDTO.setSubscribersCount(subscriptionService.calculateUserSubscribers(username));
        userDTO.setSubscriptionsCount(subscriptionService.calculateUserSubscriptions(username));
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(value = "/user/{username}")
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody UserDTO userDTO){
        if(!currentAuthentication.getUser().getUsername().equals(username)){
            return ResponseEntity.status(403).body("Can not update another user!");
        }

        if(userDTO.getAvatar()!=null) {
            userService.updateUserAvatar(username, Base64.getDecoder().decode(userDTO.getAvatar()));
        }

        if(userDTO.getBio()!=null) {
            userService.updateUserBio(username, userDTO.getBio());
        }
        return ResponseEntity.ok(new UserDTO(userService.findUserByUsername(username)));
    }


}
