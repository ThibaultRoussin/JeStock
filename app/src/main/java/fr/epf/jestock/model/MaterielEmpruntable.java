package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thibault on 13/05/2018.
 */

public class MaterielEmpruntable {

    private boolean succes;
    @SerializedName("nom")
    private String nom;
    @SerializedName("reference")
    private long Reference;
    @SerializedName("quantiteTotale")
    private int quantiteTotale;
    @SerializedName("quantiteEmpruntee")
    private int quantiteEmprunte;
    @SerializedName("quantiteNonEmpruntee")
    private int quantiteNonEmprunte;
    private int quantiteTotaleConseillee;
    private int quantiteACommander;

    public MaterielEmpruntable() {

    }

    public MaterielEmpruntable(String nom, long reference, int quantiteTotale, int quantiteEmprunte, int quantiteNonEmprunte, int quantiteTotaleConseillee, int quantiteACommander) {
        this.nom = nom;
        Reference = reference;
        this.quantiteTotale = quantiteTotale;
        this.quantiteEmprunte = quantiteEmprunte;
        this.quantiteNonEmprunte = quantiteNonEmprunte;
        this.quantiteTotaleConseillee = quantiteTotaleConseillee;
        this.quantiteACommander = quantiteACommander;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getReference() {
        return Reference;
    }

    public void setReference(long reference) {
        Reference = reference;
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public int getQuantiteEmprunte() {
        return quantiteEmprunte;
    }

    public void setQuantiteEmprunte(int quantiteEmprunte) {
        this.quantiteEmprunte = quantiteEmprunte;
    }

    public int getQuantiteNonEmprunte() {
        return quantiteNonEmprunte;
    }

    public void setQuantiteNonEmprunte(int quantiteNonEmprunte) {
        this.quantiteNonEmprunte = quantiteNonEmprunte;
    }

    public int getQuantiteTotaleConseillee() {
        return quantiteTotaleConseillee;
    }

    public void setQuantiteTotaleConseillee(int quantiteTotaleConseillee) {
        this.quantiteTotaleConseillee = quantiteTotaleConseillee;
    }

    public int getQuantiteACommander() {
        return quantiteACommander;
    }

    public void setQuantiteACommander(int quantiteACommander) {
        this.quantiteACommander = quantiteACommander;
    }
}
