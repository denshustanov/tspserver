package com.company.tspserver.entity.complaint;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.User;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "COMPLAINT")
@Entity
public class Complaint {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name = "CAUSE")
    private String cause;

    @Column(name = "STATUS")
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ComplaintCause getCause() {
        return cause ==null? null: ComplaintCause.fromId(cause);
    }

    public void setCause(ComplaintCause cause) {
        this.cause = cause==null? null: cause.getId();
    }

    public ComplaintStatus getStatus() {
        return status == null? null: ComplaintStatus.fromId(status);
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status == null? null: status.getId();
    }
}