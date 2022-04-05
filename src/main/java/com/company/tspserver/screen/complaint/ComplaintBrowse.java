package com.company.tspserver.screen.complaint;

import io.jmix.ui.screen.*;
import com.company.tspserver.entity.complaint.Complaint;

@UiController("Complaint.browse")
@UiDescriptor("complaint-browse.xml")
@LookupComponent("complaintsTable")
public class ComplaintBrowse extends StandardLookup<Complaint> {
}