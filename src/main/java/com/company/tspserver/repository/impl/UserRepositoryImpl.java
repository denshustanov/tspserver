package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.User;
import com.company.tspserver.repository.UserRepository;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    protected DataManager dataManager;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User createUser(String username, String password, String bio, byte[] avatar) {
        User user = dataManager.create(User.class);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setBio(bio);
        user.setAvatar(avatar);

        RoleAssignmentEntity baseUserRoleAssignment = dataManager.create(RoleAssignmentEntity.class);
        baseUserRoleAssignment.setUsername(username);
        baseUserRoleAssignment.setRoleCode("base-user-rest-role");
        baseUserRoleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);

        RoleAssignmentEntity restRoleAssignment = dataManager.create(RoleAssignmentEntity.class);
        restRoleAssignment.setUsername(username);
        restRoleAssignment.setRoleCode("rest-minimal");
        restRoleAssignment.setRoleType(RoleAssignmentRoleType.RESOURCE);

        dataManager.save(baseUserRoleAssignment, restRoleAssignment);

        return dataManager.save(user);
    }

    @Override
    public User updateUserBio(String username, String bio) {
        User user = findUserByUsername(username);
        user.setBio(bio);
        return dataManager.save(user);
    }

    @Override
    public User updateUserAvatar(String username, byte[] avatar) {
        User user = findUserByUsername(username);
        user.setAvatar(avatar);
        return dataManager.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return dataManager.load(User.class)
                .query("select u from User u where u.username = :username")
                .parameter("username", username).one();
    }

    @Override
    public List<User> findAllUsersByUsernameSubstring(String usernameSubstring) {
        return dataManager.load(User.class)
                .condition(PropertyCondition.contains("username", usernameSubstring))
                .list();
    }

    @Override
    public void deleteUser(User user) {
        dataManager.remove(user);
    }
}
