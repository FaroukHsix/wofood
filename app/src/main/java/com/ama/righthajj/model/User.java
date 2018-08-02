package com.ama.righthajj.model;

import java.io.Serializable;

/**
 * Created by hammo on 8/2/2018.
 */

public class User implements Serializable {
    private String fullname ;
    private int duration;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
