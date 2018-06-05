package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/*
    Nom ......... : Emprunts.java
    Role ........ : Classe récupérant les données relatives aux emprunts retournées par le serveur au format JSON
    Auteur ...... : DSI_2

*/

public class Emprunts {

    @SerializedName("reference")
    private long Reference;
    @SerializedName("nomRef")
    private String nomRef;
    private String numeroEtudiant;
    @SerializedName("prenomEmprunteur")
    private String prenomEmprunteur;
    @SerializedName("nomEmprunteur")
    private String nomEmprunteur;
    @SerializedName("dateEmprunt")
    private String dateEmprunt;
    @SerializedName("dateRendu")
    private String dateRetour;

    public Emprunts() {
    }

    public Emprunts(long reference, String nomRef, String numeroEtudiant, String prenomEmprunteur, String nomEmprunteur, String dateEmprunt, String dateRetour) {
        Reference = reference;
        this.nomRef = nomRef;
        this.numeroEtudiant = numeroEtudiant;
        this.prenomEmprunteur = prenomEmprunteur;
        this.nomEmprunteur = nomEmprunteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public long getReference() {
        return Reference;
    }

    public void setReference(long reference) {
        Reference = reference;
    }

    public String getNomRef() {
        return nomRef;
    }

    public void setNomRef(String nomRef) {
        this.nomRef = nomRef;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getPrenomEmprunteur() {
        return prenomEmprunteur;
    }

    public void setPrenomEmprunteur(String prenomEmprunteur) {
        this.prenomEmprunteur = prenomEmprunteur;
    }

    public String getNomEmprunteur() {
        return nomEmprunteur;
    }

    public void setNomEmprunteur(String nomEmprunteur) {
        this.nomEmprunteur = nomEmprunteur;
    }

    public String getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(String dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }
}
