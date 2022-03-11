package com.company.tspserver.dto;

import com.company.tspserver.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Base64;

public class UserDTO {
    private String username;
    private String password;
    private String bio;
    private String avatar;
    private int subscriptionsCount;
    private int subscribersCount;
    private int postsCount;

    public UserDTO(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("bio") String bio,
            @JsonProperty("avatar") String avatart) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.avatar = avatar;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.bio = user.getBio();
        if(user.getAvatar()!=null) {
            this.avatar = Base64.getEncoder().encodeToString(user.getAvatar());
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSubscriptionsCount() {
        return subscriptionsCount;
    }

    public void setSubscriptionsCount(int subscriptionsCount) {
        this.subscriptionsCount = subscriptionsCount;
    }

    public int getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(int subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }
}
