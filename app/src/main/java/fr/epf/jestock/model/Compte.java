package fr.epf.jestock.model;

/*
    Nom ......... : Compte.java
    Role ........ : Classe contenat le statut et le campus affilié à l'utilisateur
    Auteur ...... : DSI_2

*/

public class Compte {
    static private String statut;
    static private String campus;

    public Compte() {
    }

    public static String getStatut() {
        return statut;
    }

    public static void setStatut(String statut) {
        Compte.statut = statut;
    }

    public static String getCampus() {
        return campus;
    }

    public static void setCampus(String campus) {
        Compte.campus = campus;
    }
}
