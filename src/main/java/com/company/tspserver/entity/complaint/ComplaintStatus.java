package com.company.tspserver.entity.complaint;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;

public enum ComplaintStatus implements EnumClass<String> {
    ACTIVE("ACTIVE"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String id;

    ComplaintStatus(String id){
        this.id = id;
    }

    @Nullable
    public static ComplaintStatus fromId(String id){
        for (ComplaintStatus at: ComplaintStatus.values()){
            if(at.getId().equals(id)){
                return at;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }
}
