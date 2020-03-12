package com.example.jointplanning.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TaskJson {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("f_project")
    @Expose
    private Integer fProject;
    @SerializedName("c_description")
    @Expose
    private String cDescription;
    @SerializedName("c_author")
    @Expose
    private String cAuthor;
    @SerializedName("n_size")
    @Expose
    private Integer nSize;
    @SerializedName("d_done_date")
    @Expose
    private Date dDoneDate;
    @SerializedName("n_priority")
    @Expose
    private Integer nPriority;
    @SerializedName("jb_tag")
    @Expose
    private JsonArray jbTag;
    @SerializedName("dx_created")
    @Expose
    private Date dxCreated;
    @SerializedName("b_disabled")
    @Expose
    private Boolean bDisabled;

    public long getId() {
        return id;
    }

    public Integer getFProject() {
        return fProject;
    }

    public String getCDescription() {
        return cDescription;
    }

    public String getCAuthor() {
        return cAuthor;
    }

    public Integer getNSize() {
        return nSize;
    }

    public Date getDDoneDate() {
        return dDoneDate;
    }

    public Integer getNPriority() {
        return nPriority;
    }

    public JsonArray getJbTag() {
        return jbTag;
    }

    public Date getDxCreated() {
        return dxCreated;
    }

    public Boolean getBDisabled() {
        return bDisabled;
    }

    public void setBDisabled(Boolean bDisabled) {
        this.bDisabled = bDisabled;
    }

}
