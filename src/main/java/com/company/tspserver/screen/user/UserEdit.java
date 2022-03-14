package com.company.tspserver.screen.user;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.User;

@UiController("User.edit")
@UiDescriptor("user-edit.xml")
@EditedEntityContainer("userDc")
public class UserEdit extends StandardEditor<User> {
}