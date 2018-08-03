package com.ama.righthajj.model;

import java.io.Serializable;

/**
 * Created by hammo on 8/2/2018.
 */

public class Gate implements Serializable {
    private int entry_port;
    private int number;

    public Gate(int entry_port, int number) {
        this.entry_port = entry_port;
        this.number = number;
    }

    public int getEntry_port() {
        return entry_port;
    }

    public void setEntry_port(int entry_port) {
        this.entry_port = entry_port;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
