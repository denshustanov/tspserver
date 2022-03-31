package com.company.tspserver.screen.postcomment;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.PostComment;

@UiController("PostComment.edit")
@UiDescriptor("post-comment-edit.xml")
@EditedEntityContainer("postCommentDc")
public class PostCommentEdit extends StandardEditor<PostComment> {
}