package com.company.tspserver.screen.post.post;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.Post;

@UiController("Post.edit")
@UiDescriptor("post-edit.xml")
@EditedEntityContainer("postDc")
public class PostEdit extends StandardEditor<Post> {
}