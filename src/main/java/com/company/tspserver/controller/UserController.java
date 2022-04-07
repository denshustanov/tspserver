package com.company.tspserver.controller;

import com.company.tspserver.dto.UserDTO;
import com.company.tspserver.entity.User;
import com.company.tspserver.service.FCMService;
import com.company.tspserver.service.PostService;
import com.company.tspserver.service.SubscriptionService;
import com.company.tspserver.service.UserService;
import com.company.tspserver.service.impl.fcm.PushNotifyConf;
import com.google.firebase.messaging.FirebaseMessagingException;
import io.jmix.core.security.CurrentAuthentication;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    protected FCMService fcmService;

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
    public ResponseEntity updateUserAvatar(@PathVariable String username, @RequestBody byte[] avatar){
        if(!currentAuthentication.getUser().getUsername().equals(username)){
            return ResponseEntity.status(403).body("Can not update another user!");
        }

        userService.updateUserAvatar(username, avatar);
        return ResponseEntity.ok(new UserDTO(userService.findUserByUsername(username)));
    }

    @PutMapping(value = "/user/{username}/bio")
    public ResponseEntity updateUserBio(@PathVariable String username, @RequestBody String bio){
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

    @GetMapping(value = "/user/find")
    public ResponseEntity findUsersByUsername(@RequestParam(name = "username") String username){
        List<User> users = userService.findAllUsersByUsernameSubstring(username);

        List<UserDTO> userDTOS = new LinkedList<>();

        for(User user: users){
            userDTOS.add(new UserDTO(user));
        }
        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping(value = "/user/fcm-token")
    public ResponseEntity createUserFCMToken(@RequestParam(name = "token") String token){
        String username = currentAuthentication.getUser().getUsername();
        fcmService.createToken(username, token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/user/fcm-token")
    public ResponseEntity deleteUserFCMToken(@RequestParam(name = "token") String token){
        String username = currentAuthentication.getUser().getUsername();
        fcmService.deleteToken(fcmService.findToken(username, token));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/send-test-push/{username}")
    public ResponseEntity sendTestPush(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        PushNotifyConf conf = new PushNotifyConf();
        conf.setBody("Test notification for " + username);
        conf.setTitle("Test");
        conf.setIcon("");
        conf.setTtlInSeconds("5");
        fcmService.sendPersonal(conf, user);
        return ResponseEntity.ok().build();
    }
}
