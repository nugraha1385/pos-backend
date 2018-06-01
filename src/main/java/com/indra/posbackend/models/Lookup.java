package com.indra.posbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Indrap on 19/05/2018.
 */

@Document(collection = "lookup")
public class Lookup {

    @Id
    private String lookupId;
    @Indexed(unique = true)
    private String lookupType;
    private String description;
    private List<LookupItem> lookupItemList;

    public Lookup() {
    }

    public Lookup(String lookupId, String lookupType, String description) {
        this.lookupId = lookupId;
        this.lookupType = lookupType;
        this.description = description;
    }

    public String getLookupId() {
        return lookupId;
    }

    public void setLookupId(String lookupId) {
        this.lookupId = lookupId;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LookupItem> getLookupItemList() {
        return lookupItemList;
    }

    public void setLookupItemList(List<LookupItem> lookupItemList) {
        this.lookupItemList = lookupItemList;
    }
}
