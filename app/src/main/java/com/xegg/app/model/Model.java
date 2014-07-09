package com.xegg.app.model;

import com.xegg.app.util.ApiClientUtil;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Model {

    protected abstract JSONObject toJSONObject() throws JSONException;

    protected abstract String url();

    public void save() {
        try {
            ApiClientUtil.SaveTask task = new ApiClientUtil.SaveTask(url());
            task.execute(toJSONObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
