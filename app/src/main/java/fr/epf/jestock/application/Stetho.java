package fr.epf.jestock.application;

import android.app.Application;

import fr.epf.jestock.data.MaterielDAO;
import fr.epf.jestock.data.UserDAO;

/**
 * Created by Thibault on 27/04/2018.
 */

public class Stetho extends Application {

    public void onCreate() {
        super.onCreate();
        com.facebook.stetho.Stetho.initializeWithDefaults(this);

        /*UserDAO userBDD = new UserDAO(this);
        MaterielDAO materielBDD = new MaterielDAO(this);

        userBDD.create();
        materielBDD.create();*/

        //MaterielDAO materielBDD = new MaterielDAO(this);
        //materielBDD.create2();

    }
}
