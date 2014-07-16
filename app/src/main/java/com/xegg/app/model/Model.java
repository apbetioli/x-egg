package com.xegg.app.model;

import com.xegg.app.util.ApiClientUtil;

import org.json.JSONException;
import org.json.JSONObject;

public interface Model {

    JSONObject toJSONObject() throws JSONException;

    String url();

    void validate();
}
