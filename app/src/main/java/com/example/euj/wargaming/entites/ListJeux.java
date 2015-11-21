package com.example.euj.wargaming.entites;

/**
 * Created by Euj on 21/11/2015.
 */
public class Listjeux {
    public String image;
    public String titre;
    public String desc;

    public Listjeux(String image, String titre, String desc) {
        this.image = image;
        this.titre = titre;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
