package com.company.tspserver.screen.userfcmtoken;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.UserFCMToken;

@UiController("UserFCMToken.browse")
@UiDescriptor("user-fcm-token-browse.xml")
@LookupComponent("userFCMTokensTable")
public class UserFCMTokenBrowse extends StandardLookup<UserFCMToken> {
}