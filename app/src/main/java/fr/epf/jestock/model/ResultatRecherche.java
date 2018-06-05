package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/*
    Nom ......... : ResultatRecherche.java
    Role ........ : Classe récupérant les données relatives à l'identification d'un matériel par son code EAN
                    retournées par le serveur au format JSON
    Auteur ...... : DSI_2

*/

public class ResultatRecherche {

    @SerializedName("type")
    private String resultat;
    @SerializedName("reference")
    private long reference;
    @SerializedName("nom")
    private String nom;

    public ResultatRecherche() {
    }

    public ResultatRecherche(String resultat, long reference, String nom) {
        this.resultat = resultat;
        this.reference = reference;
        this.nom = nom;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public long getReference() {
        return reference;
    }

    public void setReference(long reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
