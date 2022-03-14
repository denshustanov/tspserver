package com.company.tspserver.screen.user;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.User;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
public class UserBrowse extends StandardLookup<User> {
}