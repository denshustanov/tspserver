package com.company.tspserver.repository;

import com.company.tspserver.entity.UserFCMToken;

import java.util.List;

public interface UserFCMTokenRepository {
    UserFCMToken createToken(String username, String token);
    void deleteToken(String username, String token);
    UserFCMToken findToken(String username, String token);
    List<UserFCMToken> findTokensByUsername(String username);
}
