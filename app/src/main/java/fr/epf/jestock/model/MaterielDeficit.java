package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/*
    Nom ......... : MaterielDeficit.java
    Role ........ : Classe récupérant les données relatives aux matériels en deficit retournées par le serveur au format JSON
    Auteur ...... : DSI_2

*/

public class MaterielDeficit {

    @SerializedName("nom")
    private String nom;
    @SerializedName("reference")
    private long reference;
    @SerializedName("quantiteACommander")
    private int quantiteACommander;

    public MaterielDeficit() {
    }

    public MaterielDeficit(String nom, long reference, int quantiteACommander) {
        this.nom = nom;
        this.reference = reference;
        this.quantiteACommander = quantiteACommander;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getReference() {
        return reference;
    }

    public void setReference(long reference) {
        this.reference = reference;
    }

    public int getQuantiteACommander() {
        return quantiteACommander;
    }

    public void setQuantiteACommander(int quantiteACommander) {
        this.quantiteACommander = quantiteACommander;
    }
}
