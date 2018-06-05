package fr.epf.jestock.application;

import android.app.Application;

import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDAO;


/*
    Nom ......... : Stetho.java
    Role ........ : Application permettant la visualisation de la base de données SQlite
    Auteur ...... : DSI_2

*/

public class Stetho extends Application {

    public void onCreate() {
        super.onCreate();
        com.facebook.stetho.Stetho.initializeWithDefaults(this);

        /*UserDAO userBDD = new UserDAO(this);
        MaterielDAO materielBDD = new MaterielDAO(this);

        userBDD.create();
        materielBDD.create();*/
    }
}
