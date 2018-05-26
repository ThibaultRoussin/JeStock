package fr.epf.jestock.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.MenuActivity;
import fr.epf.jestock.model.Emprunts;
import fr.epf.jestock.model.MaterielEmpruntable;
import fr.epf.jestock.model.MaterielEnStock;

/**
 * Created by Thibault on 11/05/2018.
 */

public class MaterielDAO {

    private Context context;
    private UserDataBaseOpenHelper helper;
    private SQLiteDatabase database;

    final String TYPE = "TYPE";

    public MaterielDAO(Context context) {
        this.context = context;
        helper = new UserDataBaseOpenHelper(context);
    }

    public void create2(){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseOpenHelper.REFERENCE2,"xxx");
        values.put(UserDataBaseOpenHelper.NAME2,"Adaptateur");
        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY,"20");
        values.put(UserDataBaseOpenHelper.QUANTITY_BORROWED,"5");
        values.put(UserDataBaseOpenHelper.QUANTITY_NOT_BORROWED,"15");
        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY_ADVISE,"25");
        values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,"5");
        database.insert(helper.TABLE_MATERIEL_LOANABLE, null, values);

        database.close();

    }

    public void create3(){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseOpenHelper.MATERIEL_NAME,"Ordinateur");
        values.put(UserDataBaseOpenHelper.MATERIEL_REFERENCE,"yyy");
        values.put(UserDataBaseOpenHelper.BORROWER_TYPE,"Eleve");
        values.put(UserDataBaseOpenHelper.BORROWER_FIRST_NAME,"Jean");
        values.put(UserDataBaseOpenHelper.BORROWER_NAME,"Dupont");
        values.put(UserDataBaseOpenHelper.BORROWER_EMAIL,"jean.dupont@epedu.fr");
        values.put(UserDataBaseOpenHelper.LOAN_DATE,"24/05/2018");
        values.put(UserDataBaseOpenHelper.RENDER_DATE,"25/05/2018");
        database.insert(helper.TABLE_LOAN, null, values);

        database.close();

    }

    public Intent rechercheBDD(String ref){
        Intent intent = new Intent(context, MenuActivity.class);
        int i = 0;

        String selectQuery1 = "SELECT  * FROM " + helper.TABLE_MATERIEL_STOCK +" WHERE " + helper.REFERENCE + " LIKE " +ref;
        SQLiteDatabase db1 = helper.getWritableDatabase();
        Cursor cursor1 = db1.rawQuery(selectQuery1, null);

        String selectQuery2 = "SELECT  * FROM " + helper.TABLE_MATERIEL_LOANABLE +" WHERE " + helper.REFERENCE2 + " LIKE " +ref;
        SQLiteDatabase db2 = helper.getWritableDatabase();
        Cursor cursor2 = db2.rawQuery(selectQuery2, null);

        if (cursor1.moveToFirst()) {
            i = 1;
            intent.putExtra(helper.REFERENCE,cursor1.getString(0));
            intent.putExtra(helper.NAME,cursor1.getString(1));
            intent.putExtra(helper.STOCK_QUANTITY,cursor1.getInt(2));
            intent.putExtra(helper.STOCK_QUANTITY_ADVISE,cursor1.getInt(3));
            intent.putExtra(helper.QUANTITY_TO_ORDER,cursor1.getInt(4));
        }
        if (cursor2.moveToFirst()) {
            i=i+2;
            intent.putExtra(helper.REFERENCE2,cursor2.getString(0));
            intent.putExtra(helper.NAME2,cursor2.getString(1));
            intent.putExtra(helper.TOTAL_QUANTITY,cursor2.getInt(2));
            intent.putExtra(helper.QUANTITY_BORROWED,cursor2.getInt(3));
            intent.putExtra(helper.QUANTITY_NOT_BORROWED,cursor2.getInt(4));
            intent.putExtra(helper.TOTAL_QUANTITY_ADVISE,cursor2.getInt(5));
            intent.putExtra(helper.QUANTITY_TO_ORDER2,cursor2.getInt(6));
        }
        if (i==0) intent.putExtra(TYPE,"Aucun");
        if (i==1) intent.putExtra(TYPE,"Stock");
        if (i==2) intent.putExtra(TYPE,"Empruntable");
        if (i==3) intent.putExtra(TYPE,"Double");

        return intent;
    }

    public List<MaterielEnStock> rechercheBDDStock(){

        List<MaterielEnStock> materielList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + helper.TABLE_MATERIEL_STOCK;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MaterielEnStock materiel = new MaterielEnStock();
                materiel.setReference(cursor.getString(0));
                materiel.setNom(cursor.getString(1));
                materiel.setQuantiteStock(cursor.getInt(2));
                materiel.setQuantiteStockConseillee(cursor.getInt(3));
                materiel.setQuantiteACommander(cursor.getInt(4));
                // Ajout du materiel a la liste
                materielList.add(materiel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return materielList;
    }

    public List<MaterielEmpruntable> rechercheBDDEmpruntable(){
        List<MaterielEmpruntable> materielList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + helper.TABLE_MATERIEL_LOANABLE;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MaterielEmpruntable materiel = new MaterielEmpruntable();
                materiel.setReference(cursor.getString(0));
                materiel.setNom(cursor.getString(1));
                materiel.setQuantiteTotale(cursor.getInt(2));
                materiel.setQuantiteEmprunte(cursor.getInt(3));
                materiel.setQuantiteNonEmprunte(cursor.getInt(4));
                materiel.setQuantiteTotaleConseillee(cursor.getInt(5));
                materiel.setQuantiteACommander(cursor.getInt(6));
                // Ajout du materiel a la liste
                materielList.add(materiel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return materielList;
    }

    public List<Emprunts> rechercheBDDEmprunts(){
        List<Emprunts> empruntsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + helper.TABLE_LOAN;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                Emprunts emprunt = new Emprunts();
                emprunt.setNumeroEtudiant(cursor.getString(3));
                emprunt.setNom(cursor.getString(5));
                emprunt.setMaterielEmprunte(cursor.getString(1));
                emprunt.setDateEmprunt(cursor.getString(7));
                emprunt.setDateRetour(cursor.getString(8));
                // Ajout de l'emprunt a la liste
                empruntsList.add(emprunt);
            } while (cursor.moveToNext());
        }


        // return list
        return empruntsList;
    }

    public void ajouterMateriel(Intent intent){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserDataBaseOpenHelper.STOCK_QUANTITY,(intent.getIntExtra("QUANTITE_STOCK",0)+1));

        if (intent.getIntExtra("QUANTITE_A_COMMANDER",0) > 0){
            values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,(intent.getIntExtra("QUANTITE_A_COMMANDER",0)-1));
        }
        else values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,0);

        database.update(helper.TABLE_MATERIEL_STOCK, values, helper.REFERENCE + " LIKE " + intent.getStringExtra("REFERENCE"), null);
    }

    public void retirerMateriel(Intent intent) {

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (intent.getIntExtra("QUANTITE_STOCK",0) != 0){

            values.put(UserDataBaseOpenHelper.STOCK_QUANTITY,(intent.getIntExtra("QUANTITE_STOCK",0)-1));

            if ((intent.getIntExtra("QUANTITE_STOCK",0)-1) < (intent.getIntExtra("QUANTITE_STOCK_CONSEILLEE",0))){
                values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,(intent.getIntExtra("QUANTITE_STOCK_CONSEILLEE",0)
                        - (intent.getIntExtra("QUANTITE_STOCK",0)-1)));
            }
            else  values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,0);

            database.update(helper.TABLE_MATERIEL_STOCK, values, helper.REFERENCE + " LIKE " + intent.getStringExtra("REFERENCE"), null);

        }else{

        }
    }
}
