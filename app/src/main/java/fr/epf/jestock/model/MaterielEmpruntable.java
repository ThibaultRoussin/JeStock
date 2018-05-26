package fr.epf.jestock.model;

/**
 * Created by Thibault on 13/05/2018.
 */

public class MaterielEmpruntable {

    private String reference;
    private String nom;
    private int quantiteTotale;
    private int quantiteEmprunte;
    private int quantiteNonEmprunte;
    private int quantiteTotaleConseillee;
    private int quantiteACommander;

    public MaterielEmpruntable() {
    }

    public MaterielEmpruntable(String reference, String nom, int quantiteTotale, int quantiteEmprunte, int quantiteNonEmprunte, int quantiteTotaleConseillee, int quantiteACommander) {
        this.reference = reference;
        this.nom = nom;
        this.quantiteTotale = quantiteTotale;
        this.quantiteEmprunte = quantiteEmprunte;
        this.quantiteNonEmprunte = quantiteNonEmprunte;
        this.quantiteTotaleConseillee = quantiteTotaleConseillee;
        this.quantiteACommander = quantiteACommander;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
