package com.example.filmrecyclerview;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "table_des_films")
public class Film {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "titre") public String titre;

    @ColumnInfo(name = "titreOriginal") public String titreOriginal;

    @ColumnInfo(name = "description") public String description;

    @ColumnInfo(name = "urlImage") public String urlImage;

    @ColumnInfo(name = "dateSortie") public String dateSortie;

    @ColumnInfo(name = "langueOriginale") public String langueOriginale;

    public Film(String nom,String description, String vraiNom,  String URL, String date, String VO) {

        this.titre = nom;
        this.description = description;
        this.titreOriginal = vraiNom;
        this.urlImage = URL;
        this.dateSortie = date;
        this.langueOriginale = VO;

    }

    public Film() {

    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitreOriginal() {
        return titreOriginal;
    }

    public void setTitreOriginal(String titreOriginal) {
        this.titreOriginal = titreOriginal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getLangueOriginale() {
        return langueOriginale;
    }

    public void setLangueOriginale(String langueOriginale) {this.langueOriginale = langueOriginale;}
}
