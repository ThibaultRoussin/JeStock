package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/*
    Nom ......... : Etudiant.java
    Role ........ : Classe récupérant les données relatives aux étudiants retournées par le serveur au format JSON
    Auteur ...... : DSI_2

*/

public class Etudiant {

    @SerializedName("succes")
    private boolean succes;
    @SerializedName("numEtu")
    private String numEtu;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;

    public Etudiant() {
    }

    public Etudiant(boolean succes, String numEtu, String nom, String prenom) {
        this.succes = succes;
        this.numEtu = numEtu;
        this.nom = nom;
        this.prenom = prenom;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getNumEtu() {
        return numEtu;
    }

    public void setNumEtu(String numEtu) {
        this.numEtu = numEtu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
