package com.company.tspserver.screen.post;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.Post;

@UiController("Post.browse")
@UiDescriptor("post-browse.xml")
@LookupComponent("postsTable")
public class PostBrowse extends StandardLookup<Post> {
}