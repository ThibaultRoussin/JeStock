package fr.epf.jestock.model;

/**
 * Created by Thibault on 28/05/2018.
 */

public class Compte {
    static private String droit;
    static private String campus;

    public Compte() {
    }

    public Compte(String droit, String campus){
        Compte.droit = droit;
        Compte.campus = campus;
    }

    public static String getDroit() {
        return droit;
    }

    public static void setDroit(String droit) {
        Compte.droit = droit;
    }

    public static String getCampus() {
        return campus;
    }

    public static void setCampus(String campus) {
        Compte.campus = campus;
    }
}
