package com.example.jointplanning.model;

import java.util.Date;

public class Project {
    public Long id;
    public String c_name;
    public String c_description;
    public String c_author;
    public Date dx_created;
    public boolean b_disabled;

    public Long getId() {
        return id;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_description() {
        return c_description;
    }

    public String getC_author() {
        return c_author;
    }

    public Date getDx_created() {
        return dx_created;
    }

    public boolean isB_disabled() {
        return b_disabled;
    }
}
