package com.xegg.app.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Model implements Serializable {

    private String _id;
    private String created;

    public void validate() {}

    public String get_id() {
        return _id;
    }

    public String getCreated() {
        return created;
    }
}
