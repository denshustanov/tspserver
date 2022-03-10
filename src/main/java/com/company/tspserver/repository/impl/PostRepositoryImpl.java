package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.PostRepository;
import com.company.tspserver.repository.UserRepository;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    protected DataManager dataManager;

    @Inject
    protected UserRepository userRepository;

    @Override
    public List<Post> findPostByAuthorUsername(String username) {
        User author = userRepository.findUserByUsername(username);
        return dataManager.load(Post.class)
                .condition(PropertyCondition.equal("author", author))
                .list();
    }

    @Override
    public List<Post> findPostByText(String text) {
        return dataManager.load(Post.class)
                .condition(PropertyCondition.contains("text", text))
                .list();
    }

    @Override
    public Post createPost(User user, String text, List<byte[]> attachments) {
        Post post = dataManager.create(Post.class);
        post.setAuthor(user);
        post.setText(text);
        List<PostAttachment> postAttachments = new LinkedList<>();
        for(byte[] attachment: attachments){
            PostAttachment postAttachment = dataManager.create(PostAttachment.class);
            postAttachment.setImage(attachment);
            postAttachment.setPost(post);
            postAttachments.add(postAttachment);
        }
        post.setPostAttachments(postAttachments);
        return dataManager.save(post);
    }

    @Override
    public void deletePost(Post post) {
        dataManager.remove(post);
    }
}
