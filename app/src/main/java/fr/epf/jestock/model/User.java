package fr.epf.jestock.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thibault on 27/04/2018.
 */

public class User {

    @SerializedName("succes")
    private boolean succes;
    @SerializedName("prenom")
    private  String firstName;
    @SerializedName("nom")
    private  String lastName;
    @SerializedName("email")
    private  String email;
    @SerializedName("statut")
    private  String statut;
    @SerializedName("campus")
    private  String campus;

    public User() {
    }

    public User(String firstName, String lastName, String email, String statut, String campus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.statut = statut;
        this.campus = campus;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
