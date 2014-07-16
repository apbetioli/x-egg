package com.xegg.app.model;

import com.xegg.app.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Tag implements Model {

    private String name;
    private String image;
    private String language;
    private String country;
    private String created;

    public Tag() {
    }

    public Tag(JSONObject json) throws JSONException {
        name = json.getString("name");
        image = json.getString("image");
        language = json.getString("language");
        country = json.getString("country");
        created = json.getString("created");
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("image", image);
        json.put("language", language);
        json.put("country", country);
        return json;
    }

    @Override
    public String url() {
        return Constants.URL_TAGS;
    }

    @Override
    public void validate() {
        //TODO
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCreated() {
        return created;
    }
}
