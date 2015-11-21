package com.example.euj.wargaming.entites;

/**
 * Created by Bouba on 19/11/2015.
 */
public class Joueurtanks {
    public String image;
    public String tag;
    public String id;
    public String victoires;
    public String batailles;

    public Joueurtanks(String image, String tag) {
        this.image = image;
        this.tag = tag;
    }

    public Joueurtanks(String image, String tag, String id, String victoires, String batailles) {
        this.image = image;
        this.tag = tag;
        this.id = id;
        this.victoires = victoires;
        this.batailles = batailles;
    }

    public Joueurtanks() {
    }

    public String getVictoires() {
        return victoires;
    }

    public void setVictoires(String victoires) {
        this.victoires = victoires;
    }

    public String getBatailles() {
        return batailles;
    }

    public void setBatailles(String batailles) {
        this.batailles = batailles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
