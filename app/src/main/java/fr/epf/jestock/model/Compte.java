package fr.epf.jestock.model;

/**
 * Created by Thibault on 28/05/2018.
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
