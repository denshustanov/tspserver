package com.company.tspserver.screen.postcomment;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.PostComment;

@UiController("PostComment.browse")
@UiDescriptor("post-comment-browse.xml")
@LookupComponent("postCommentsTable")
public class PostCommentBrowse extends StandardLookup<PostComment> {
}