package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thibault on 01/06/2018.
 */

public class ResultatModifQuantite {

    @SerializedName("succes")
    private boolean succes;

    public ResultatModifQuantite() {
    }

    public ResultatModifQuantite(boolean succes) {
        this.succes = succes;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

}
