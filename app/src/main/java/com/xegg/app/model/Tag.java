package com.xegg.app.model;

import java.util.Comparator;

public class Tag extends Model implements Comparable<Tag> {

    private String name;
    private String image;
    private String language;
    private String country;

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



    @Override
    public int compareTo(Tag other) {
        return this.getName().compareTo(other.getName());
    }
}
