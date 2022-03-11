package com.company.tspserver.screen.subscription;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.Subscription;

@UiController("Subscription.browse")
@UiDescriptor("subscription-browse.xml")
@LookupComponent("subscriptionsTable")
public class SubscriptionBrowse extends StandardLookup<Subscription> {
}