package com.company.tspserver.entity.complaint;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;

public enum ComplaintCause implements EnumClass<String> {
    SPAM("SPAM"),
    FRAUD("FRAUD"),
    VIOLENT_CONTENT("VIOLENT"),
    ADULT_CONTENT("ADULT"),
    ABUSE("ABUSE");

    private final String id;

    ComplaintCause(String id){
        this.id = id;
    }

    @Nullable
    public static ComplaintCause fromId(String id) {
        for(ComplaintCause at: ComplaintCause.values()){
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
