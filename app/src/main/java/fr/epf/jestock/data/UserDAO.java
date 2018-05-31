package fr.epf.jestock.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.model.MaterielEnStock;
import fr.epf.jestock.model.User;

/**
 * Created by Thibault on 27/04/2018.
 */

public class UserDAO {

    private Context context;
    private UserDataBaseOpenHelper helper;
    private SQLiteDatabase database;

    public UserDAO(Context context) {
        this.context = context;
        helper = new UserDataBaseOpenHelper(context);
    }

    /*public void create(){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseOpenHelper.LOGIN,"S");
        values.put(UserDataBaseOpenHelper.PASSWORD,"A");
        values.put(UserDataBaseOpenHelper.EMAIL,"sa@gmail.com");
        values.put(UserDataBaseOpenHelper.RIGHT,"admin");
        database.insert(helper.TABLE_USERS, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(UserDataBaseOpenHelper.LOGIN,"T");
        values2.put(UserDataBaseOpenHelper.PASSWORD,"R");
        values2.put(UserDataBaseOpenHelper.EMAIL,"ta@gmail.com");
        values2.put(UserDataBaseOpenHelper.RIGHT,"admin");
        database.insert(helper.TABLE_USERS, null, values2);

        database.close();
    }

    public List<User> connexion(){
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + helper.TABLE_USERS;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setLogin(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setDroit(cursor.getString(4));
                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return contact list
        return userList;
    }*/


}
