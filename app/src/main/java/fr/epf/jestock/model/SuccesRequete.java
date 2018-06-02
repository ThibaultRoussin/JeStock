package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thibault on 01/06/2018.
 */

public class SuccesRequete {

    @SerializedName("succes")
    private boolean succes;

    public SuccesRequete() {
    }

    public SuccesRequete(boolean succes) {
        this.succes = succes;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

}
