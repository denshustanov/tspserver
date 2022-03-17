package com.company.tspserver.screen.user;

import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.tspserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@UiController("User.edit")
@UiDescriptor("user-edit.xml")
@EditedEntityContainer("userDc")
public class UserEdit extends StandardEditor<User> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InstanceContainer<User> userDc;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        User user = userDc.getItem();
        String password = user.getPassword();

        user.setPassword(passwordEncoder.encode(password));
    }
}