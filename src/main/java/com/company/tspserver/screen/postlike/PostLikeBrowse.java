package com.company.tspserver.screen.postlike;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.PostLike;

@UiController("PostLike.browse")
@UiDescriptor("post-like-browse.xml")
@LookupComponent("postLikesTable")
public class PostLikeBrowse extends StandardLookup<PostLike> {
}