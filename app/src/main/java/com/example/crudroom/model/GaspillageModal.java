package com.example.crudroom.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "gaspillage_table")
public class GaspillageModal {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String typeDeDechet;
    private String quantite;
    private String lieu;

    public GaspillageModal(String typeDeDechet, String quantite, String lieu) {
        this.typeDeDechet = typeDeDechet;
        this.quantite = quantite;
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeDeDechet() {
        return typeDeDechet;
    }

    public void setTypeDeDechet(String typeDeDechet) {
        this.typeDeDechet = typeDeDechet;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
