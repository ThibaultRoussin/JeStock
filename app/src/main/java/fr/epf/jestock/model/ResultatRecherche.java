package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thibault on 31/05/2018.
 */

public class ResultatRecherche {

    @SerializedName("type")
    private String resultat;

    public ResultatRecherche() {
    }

    public ResultatRecherche(String resultat) {
        this.resultat = resultat;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
