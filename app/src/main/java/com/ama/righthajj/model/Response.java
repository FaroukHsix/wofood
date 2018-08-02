package com.ama.righthajj.model;

import java.io.Serializable;

/**
 * Created by hammo on 8/2/2018.
 */

public class Response<T> implements Serializable{
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    boolean error;
    String message;
    T payload;
}
