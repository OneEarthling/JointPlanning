package com.example.jointplanning.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Task {
    private long id;
    private Integer f_project;
    private String c_description;
    private String c_author;
    private Integer n_size;
    private Date d_done_date;
    private Integer n_priority;
    private JsonArray jb_tag;
    private Date dx_created;
    private Boolean b_disabled;

    public long getId() {
        return id;
    }

    public Integer getF_project() {
        return f_project;
    }

    public String getC_description() {
        return c_description;
    }

    public String getC_author() {
        return c_author;
    }

    public Integer getN_size() {
        return n_size;
    }

    public Date getD_done_date() {
        return d_done_date;
    }

    public Integer getN_priority() {
        return n_priority;
    }

    public JsonArray getJb_tag() {
        return jb_tag;
    }

    public Date getDx_created() {
        return dx_created;
    }

    public Boolean getB_disabled() {
        return b_disabled;
    }

}
