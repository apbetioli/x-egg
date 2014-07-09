package com.xegg.app.model;

import com.xegg.app.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Post extends Model {

    private String description;
    private String image;
    private String author;
    private String language;
    private String country;
    private String tag;
    private String created;

    public Post() {
    }

    public Post(JSONObject json) throws JSONException {
        description = json.getString("description");
        image = json.getString("image");
        author = json.getString("author");
        language = json.getString("language");
        country = json.getString("country");
        tag = json.getString("tag");
        created = json.getString("created");
    }

    @Override
    protected JSONObject toJSONObject() throws JSONException {
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
    protected String url() {
        return Constants.URL_POSTS;
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
