package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/*
    Nom ......... : SuccesRequete.java
    Role ........ : Classe récupérant le succes d'une opération sur la BDD retournées par le serveur au format JSON
    Auteur ...... : DSI_2

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
