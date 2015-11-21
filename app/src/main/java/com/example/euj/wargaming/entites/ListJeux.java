package com.example.euj.wargaming.entites;

/**
 * Created by Euj on 07/11/2015.
 */
public class ListJeux {
    public String Image;
    public String Titre;
    public String Desc;
public ListJeux()
{
    super();
}
    public ListJeux(String image, String titre, String desc) {
        super();
        Image = image;
        Titre = titre;
        Desc = desc;
    }
    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        Image = image;
    }
    public String getTitre() {
        return Titre;
    }
    public void setTitre(String titre) {
        Titre = titre;
    }
    public String getDesc() {
        return Desc;
    }
    public void setDesc(String desc) {
        Desc = desc;
    }

}