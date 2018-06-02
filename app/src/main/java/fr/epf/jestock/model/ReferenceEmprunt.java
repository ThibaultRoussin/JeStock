package fr.epf.jestock.model;

/**
 * Created by Thibault on 02/06/2018.
 */

public class ReferenceEmprunt {

    private static Long Reference = 0L;
    private static String nom = "";

    public ReferenceEmprunt() {
    }

    public static Long getReference() {
        return Reference;
    }

    public static void setReference(Long reference) {
        Reference = reference;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        ReferenceEmprunt.nom = nom;
    }
}
