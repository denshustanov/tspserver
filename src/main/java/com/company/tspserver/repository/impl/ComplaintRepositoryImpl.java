package com.company.tspserver.repository.impl;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.User;
import com.company.tspserver.entity.complaint.Complaint;
import com.company.tspserver.entity.complaint.ComplaintCause;
import com.company.tspserver.entity.complaint.ComplaintStatus;
import com.company.tspserver.repository.ComplaintRepository;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComplaintRepositoryImpl implements ComplaintRepository{
    @Autowired
    protected DataManager dataManager;

    @Override
    public Complaint createComplaint(User user, Post post, ComplaintCause cause) {
        Complaint complaint = dataManager.create(Complaint.class);
        complaint.setPost(post);
        complaint.setUser(user);
        complaint.setCause(cause);
        complaint.setStatus(ComplaintStatus.ACTIVE);

        return dataManager.save(complaint);
    }

    @Override
    public List<Complaint> findAllComplaintsByPost(Post post) {
        return dataManager.load(Complaint.class)
                .condition(PropertyCondition.equal("post", post))
                .list();
    }

    @Override
    public List<Complaint> findAllComplaintsByUser(User user) {
        return dataManager.load(Complaint.class)
                .condition(PropertyCondition.equal("user", user))
                .list();
    }

    @Override
    public List<Complaint> findAllComplaintsByUserPosts(User user) {
        return dataManager.load(Complaint.class)
                .query("select c from Complaint c where c.post.author = :user")
                .parameter("user", user)
                .list();
    }

    @Override
    public void deleteComplaint(Complaint complaint) {
        dataManager.remove(complaint);
    }

    @Override
    public Complaint acceptComplaint(Complaint complaint) {
        complaint.setStatus(ComplaintStatus.ACCEPTED);
        return dataManager.save(complaint);
    }

    @Override
    public Complaint rejectComplaint(Complaint complaint) {
        complaint.setStatus(ComplaintStatus.REJECTED);
        return dataManager.save(complaint);
    }
}
