package com.company.tspserver.controller;

import com.company.tspserver.dto.SubscriptionDTO;
import com.company.tspserver.dto.UserDTO;
import com.company.tspserver.entity.Subscription;
import com.company.tspserver.entity.User;
import com.company.tspserver.service.SubscriptionService;
import io.jmix.core.security.CurrentAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class SubscriptionController {
    @Autowired
    protected SubscriptionService subscriptionService;

    @Autowired
    protected CurrentAuthentication currentAuthentication;

    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @PostMapping(value = "/subscription")
    ResponseEntity subscribe(@RequestParam(name = "subscriber") String subscriberUsername, @RequestParam(name = "subscription") String subscriptionUsername){
        logger.info(subscriberUsername + ' ' + subscriptionUsername);
        logger.info(currentAuthentication.getUser().getUsername());
        if(!currentAuthentication.getUser().getUsername().equals(subscriberUsername)){
            return ResponseEntity.status(403).body("User can subscribe only himself");
        }
        Subscription subscription = subscriptionService.subscribeToUser(subscriberUsername, subscriptionUsername);
        return ResponseEntity.ok(new SubscriptionDTO(subscription));
    }

    @GetMapping(value = "/user/{username}/subscriptions")
    ResponseEntity getUserSubscriptions(@PathVariable String username){
        List<Subscription> subscriptions = subscriptionService.findAllUserSubscriptions(username);

        List<UserDTO> subscriptionDTOs = new LinkedList<>();

        for (Subscription subscription: subscriptions) {
            subscriptionDTOs.add(new UserDTO(subscription.getSubscription()));
        }

        return ResponseEntity.ok(subscriptionDTOs);
    }

    @GetMapping(value = "/user/{username}/subscribers")
    ResponseEntity getUserSubscribers(@PathVariable String username){
        List<Subscription> subscribers = subscriptionService.findAllUserSubscribers(username);

        List<UserDTO> subscriberDTOs = new LinkedList<>();

        for (Subscription subscription: subscribers) {
            subscriberDTOs.add(new UserDTO(subscription.getSubscriber()));
        }

        return ResponseEntity.ok(subscriberDTOs);
    }

    @GetMapping(value = "/subscription")
    ResponseEntity checkSubscription(@RequestParam(name = "subscriber") String subscriberUsername, @RequestParam(name = "subscription") String subscriptionUsername){
        logger.info(subscriberUsername + ' ' + subscriptionUsername);
        return ResponseEntity.ok(subscriptionService.checkSubscription(subscriberUsername, subscriptionUsername));
    }

    @DeleteMapping(value = "/subscription")
    ResponseEntity deleteSubscription(@RequestParam(name = "subscriber") String subscriberUsername, @RequestParam(name = "subscription") String subscriptionUsername){
        String username = currentAuthentication.getUser().getUsername();
        if(!username.equals(subscriberUsername) && !username.equals(subscriptionUsername)){
            return ResponseEntity.status(403).body("User can delete only his subscriptions or subscribers!");
        }

        subscriptionService.deleteSubscription(subscriberUsername, subscriptionUsername);
        return ResponseEntity.ok().build();
    }
}
