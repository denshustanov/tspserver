package com.company.tspserver.dto;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.PostAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import liquibase.pro.packaged.U;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PostDTO {
    private String text;
    private LocalDateTime publicationDate;
    private List<String> attachments;
    private List<String> attachmentIDs;
    private UserDTO author;
    private UUID id;
    private int likesCount = 0;
    private int commentsCount = 0;

    public PostDTO(
            @JsonProperty("author") UserDTO author,
            @JsonProperty("text") String text,
            @JsonProperty("publicationDate") LocalDateTime publicationDate,
            @JsonProperty("attachments") List<String> attachments,
            @JsonProperty("attachmentIDs") List<String> attachmentIDs
//            @JsonProperty("likesCount") int likesCount,
//            @JsonProperty("commentsCount") int commentsCount
            ) {
        this.author = author;
        this.text = text;
        this.publicationDate = publicationDate;
        this.attachments = attachments;
        this.attachmentIDs = attachmentIDs;
//        this.likesCount = likesCount;
//        this.commentsCount = commentsCount;
    }

    public PostDTO(Post post){
        this.id = post.getId();
        this.author = new UserDTO(post.getAuthor());
        this.text = post.getText();
        this.attachmentIDs = new LinkedList<>();
        for(PostAttachment postAttachment: post.getPostAttachments()){
            attachmentIDs.add(postAttachment.getId().toString());
        }
        this.publicationDate = post.getPublicationDate();
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

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getAttachmentIDs() {
        return attachmentIDs;
    }

    public void setAttachmentIDs(List<String> attachmentIDs) {
        this.attachmentIDs = attachmentIDs;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}
