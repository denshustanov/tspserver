package com.company.tspserver.repository;

import com.company.tspserver.entity.Post;
import com.company.tspserver.entity.User;
import com.company.tspserver.entity.complaint.Complaint;
import com.company.tspserver.entity.complaint.ComplaintCause;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ComplaintRepository {

   Complaint createComplaint(User user, Post post, ComplaintCause cause);
   List<Complaint> findAllComplaintsByPost(Post post);
   List<Complaint> findAllComplaintsByUser(User user);
   List<Complaint> findAllComplaintsByUserPosts(User user);
   void deleteComplaint(Complaint complaint);
   Complaint acceptComplaint(Complaint complaint);
   Complaint rejectComplaint(Complaint complaint);
}
