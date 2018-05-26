package fr.epf.jestock.model;

/**
 * Created by Utilisateur on 25/05/2018.
 */

public class Emprunts {

    private String numeroEtudiant;
    private String nom;
    private String materielEmprunte;
    private String dateEmprunt;
    private String dateRetour;

    public Emprunts() {
    }

    public Emprunts(String numeroEtudiant, String nom, String materielEmprunte, String dateEmprunt, String dateRetour) {
        this.numeroEtudiant = numeroEtudiant;
        this.nom = nom;
        this.materielEmprunte = materielEmprunte;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMaterielEmprunte() {
        return materielEmprunte;
    }

    public String getDateEmprunt() {
        return dateEmprunt;
    }

    public String getDateRetour() {
        return dateRetour;
    }
    public void setMaterielEmprunte(String materielEmprunte) {
        this.materielEmprunte = materielEmprunte;
    }

    public void setDateEmprunt(String dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

}
