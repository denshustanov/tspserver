package com.company.tspserver.controller;

import com.company.tspserver.dto.UserDTO;
import com.company.tspserver.entity.User;
import com.company.tspserver.service.UserService;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
public class UserController {
    @Autowired
    protected UserService userService;
    @Autowired
    protected CurrentAuthentication currentAuthentication;

    //test
    @Autowired
    protected DataManager dataManager;

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
        return ResponseEntity.ok(new UserDTO(user));
    }


}
