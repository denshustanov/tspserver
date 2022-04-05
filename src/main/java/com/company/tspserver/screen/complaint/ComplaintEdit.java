package com.company.tspserver.screen.complaint;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.complaint.Complaint;

@UiController("Complaint.edit")
@UiDescriptor("complaint-edit.xml")
@EditedEntityContainer("complaintDc")
public class ComplaintEdit extends StandardEditor<Complaint> {
}