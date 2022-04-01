package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;
import com.company.tspserver.entity.User;
import com.company.tspserver.repository.PostRepository;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.Sort;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    @Autowired
    protected DataManager dataManager;

    @Override
    public List<Post> findPostByAuthor(User user) {
        return dataManager.load(Post.class)
                .condition(PropertyCondition.equal("author", user))
                .list();
    }

    @Override
    public List<Post> findPostByText(String text) {
        return dataManager.load(Post.class)
                .condition(PropertyCondition.contains("text", text))
                .list();
    }

    @Override
    public Post createPost(User user, String text, List<String> attachments, LocalDateTime publicationDate) {
        Post post = dataManager.create(Post.class);
        post.setAuthor(user);
        post.setText(text);
        post.setPublicationDate(publicationDate);
        post = dataManager.save(post);
        if(attachments!=null) {
            List<PostAttachment> postAttachments = new LinkedList<>();
            SaveContext context = new SaveContext();
            for (String attachment : attachments) {
                PostAttachment postAttachment = dataManager.create(PostAttachment.class);
                postAttachment.setImage(Base64.getDecoder().decode(attachment));
                postAttachment.setPost(post);
                postAttachments.add(postAttachment);
                context.saving(postAttachment);
            }
            post.setPostAttachments(postAttachments);
            dataManager.save(context);
        }
        return post;
    }

    @Override
    public void deletePost(Post post) {
        dataManager.remove(post);
    }

    @Override
    public Post findPostById(UUID id) {
        return dataManager.load(Post.class)
                .id(id).one();
    }

    @Override
    public PostAttachment findPostAttachmentById(UUID id) {
        return dataManager.load(PostAttachment.class)
                .id(id)
                .one();
    }

    @Override
    public List<Post> findAllPosts() {
        return dataManager.load(Post.class).all()
                .firstResult(0)
                .maxResults(10)
                .list();
    }

    @Override
    public void deleteAllUserPosts(User user) {
        List<Post> posts = findPostByAuthor(user);
        dataManager.remove(posts);
    }

    @Override
    public List<Post> loadSubscriptionsPosts(User user, int offset) {
        List<Post> posts =  dataManager.load(Post.class)
                .query("select p from Post p, Subscription s where p.author = s.subscription and s.subscriber = :user or p.author = :user")
                .parameter("user", user)
                .sort(Sort.by(Sort.Direction.DESC, "publicationDate"))
                .firstResult(offset)
                .maxResults(10)
                .list();

        return posts.stream().distinct().collect(Collectors.toList());
    }
}
