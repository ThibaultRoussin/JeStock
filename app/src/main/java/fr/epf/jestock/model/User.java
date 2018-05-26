package fr.epf.jestock.model;

/**
 * Created by Thibault on 27/04/2018.
 */

public class User {

    private String login;
    private String password;
    private String email;
    private String droit;

    public User() {
    }

    public User(String login, String password, String email, String droit) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.droit = droit;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDroit() {
        return droit;
    }

    public void setDroit(String droit) {
        this.droit = droit;
    }
}
