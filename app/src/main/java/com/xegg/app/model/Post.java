package com.xegg.app.model;

import com.xegg.app.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Post implements Model {

    private String description;
    private String image;
    private String author;
    private String language;
    private String country;
    private String tag;
    private String created;

    public Post() {
    }

    public Post(JSONObject json) {
        try { description = json.getString("description"); } catch(JSONException e) {};
        try { image = json.getString("image"); } catch(JSONException e) {};
        try { author = json.getString("author"); } catch(JSONException e) {};
        try { language = json.getString("language"); } catch(JSONException e) {};
        try { country = json.getString("country"); } catch(JSONException e) {};
        try { tag = json.getString("tag"); } catch(JSONException e) {};
        try { created = json.getString("created"); } catch(JSONException e) {};
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("image", image);
        json.put("author", author);
        json.put("language", language);
        json.put("country", country);
        json.put("tag", tag);
        return json;
    }

    @Override
    public String url() {
        return Constants.URL_POSTS;
    }

    @Override
    public void validate() {
        //TODO
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCreated() {
        return created;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
