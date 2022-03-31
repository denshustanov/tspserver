package com.company.tspserver.dto;

import com.company.tspserver.entity.PostComment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostCommentDTO {
    UUID id;
    UUID postId;
    String text;
    UserDTO author;
    LocalDateTime publicationDate;

    public PostCommentDTO(
            @JsonProperty("postId") UUID postId,
            @JsonProperty("text") String text,
            @JsonProperty("author") UserDTO author,
            @JsonProperty("publicationDate") LocalDateTime publicationDate) {
        this.text = text;
        this.postId = postId;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public PostCommentDTO(PostComment postComment){
        this.text = postComment.getText();
        this.author = new UserDTO(postComment.getAuthor());
        this.id = postComment.getId();
        this.publicationDate = postComment.getPublcationDate();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
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

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }
}
