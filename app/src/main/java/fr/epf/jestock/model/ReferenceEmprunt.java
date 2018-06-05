package fr.epf.jestock.model;

/*
    Nom ......... : ReferenceEmprunt.java
    Role ........ : Classe contenant la référence et le nom du matériel scanné.
    Auteur ...... : DSI_2

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
