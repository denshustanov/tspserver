package com.company.tspserver.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.FetchPlan;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "POST_COMMENT")
@Entity
public class PostComment {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private User author;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "PUBLICATION_DATE")
    private LocalDateTime publcationDate;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getPublcationDate() {
        return publcationDate;
    }

    public void setPublicationDate(LocalDateTime publcationDate) {
        this.publcationDate = publcationDate;
    }
}