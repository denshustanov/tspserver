package com.company.tspserver.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "POST")
@Entity
public class Post {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TEXT")
    protected String text;

    @Column(name = "PUBLICATION_DATE")
    protected LocalDateTime publicationDate;

//    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostAttachment> postAttachments;

//    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "AUTHOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "post")
    protected List<PostLike> postLikes;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "post")
    protected List<PostComment> postComments;

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public List<PostAttachment> getPostAttachments() {
        return postAttachments;
    }

    public void setPostAttachments(List<PostAttachment> postAttachments) {
        this.postAttachments = postAttachments;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}