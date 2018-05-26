package fr.epf.jestock;

import android.app.Application;

/**
 * Created by Thibault on 27/04/2018.
 */

public class Stetho extends Application {

    public void onCreate() {
        super.onCreate();
        com.facebook.stetho.Stetho.initializeWithDefaults(this);
    }
}
