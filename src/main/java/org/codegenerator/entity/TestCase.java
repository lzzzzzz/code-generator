package org.codegenerator.entity;

/**
 * Created by LZ on 2017/7/19.
 */
public class TestCase {

    private String internalid;
    private String externalid;
    private String name;
    private String summary;
    private String preconditions;
    private String actions;
    private String expectedresults;

    public String getInternalid() {
        return internalid;
    }

    public void setInternalid(String internalid) {
        this.internalid = internalid;
    }

    public String getExternalid() {
        return externalid;
    }

    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getExpectedresults() {
        return expectedresults;
    }

    public void setExpectedresults(String expectedresults) {
        this.expectedresults = expectedresults;
    }
}
