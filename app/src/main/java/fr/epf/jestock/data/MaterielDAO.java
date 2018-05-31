package fr.epf.jestock.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.epf.jestock.MenuActivity;
import fr.epf.jestock.RefInconnueActivity;
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

    /*public void create(){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseOpenHelper.REFERENCE2,"9770317847001");
        values.put(UserDataBaseOpenHelper.NAME2,"Adaptateur");
        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY,"20");
        values.put(UserDataBaseOpenHelper.QUANTITY_BORROWED,"5");
        values.put(UserDataBaseOpenHelper.QUANTITY_NOT_BORROWED,"15");
        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY_ADVISE,"25");
        values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,"5");
        database.insert(UserDataBaseOpenHelper.TABLE_MATERIEL_LOANABLE, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(UserDataBaseOpenHelper.REFERENCE,"5901234123457");
        values2.put(UserDataBaseOpenHelper.NAME,"Test");
        values2.put(UserDataBaseOpenHelper.STOCK_QUANTITY,10);
        values2.put(UserDataBaseOpenHelper.STOCK_QUANTITY_ADVISE,7);
        values2.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,0);
        database.insert(UserDataBaseOpenHelper.TABLE_MATERIEL_STOCK, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(UserDataBaseOpenHelper.MATERIEL_NAME,"Adapteur");
        values3.put(UserDataBaseOpenHelper.MATERIEL_REFERENCE,"9770317847001");
        values3.put(UserDataBaseOpenHelper.BORROWER_TYPE,"Eleve");
        values3.put(UserDataBaseOpenHelper.BORROWER_FIRST_NAME,"Jean");
        values3.put(UserDataBaseOpenHelper.BORROWER_NAME,"Dupont");
        values3.put(UserDataBaseOpenHelper.BORROWER_EMAIL,"jean.dupont@epedu.fr");
        values3.put(UserDataBaseOpenHelper.LOAN_DATE,"24/05/2018");
        values3.put(UserDataBaseOpenHelper.RENDER_DATE,"25/05/2018");
        database.insert(UserDataBaseOpenHelper.TABLE_LOAN, null, values3);

        database.close();
    }


    public Intent rechercheBDD(String ref){
        Intent intent = new Intent(context, MenuActivity.class);
        int i = 0;

        String selectQuery2 = "SELECT  * FROM " + UserDataBaseOpenHelper.TABLE_MATERIEL_LOANABLE +" WHERE "
                + UserDataBaseOpenHelper.REFERENCE2 + " LIKE " +ref;
        SQLiteDatabase db2 = helper.getWritableDatabase();
        Cursor cursor2 = db2.rawQuery(selectQuery2, null);

        String selectQuery1 = "SELECT  * FROM " + UserDataBaseOpenHelper.TABLE_MATERIEL_STOCK +" WHERE "
                + UserDataBaseOpenHelper.REFERENCE + " LIKE " +ref;
        SQLiteDatabase db1 = helper.getWritableDatabase();
        Cursor cursor1 = db1.rawQuery(selectQuery1, null);

        if (cursor1.moveToFirst()) {
            i = 1;
            intent.putExtra(UserDataBaseOpenHelper.REFERENCE,cursor1.getString(0));
            intent.putExtra(UserDataBaseOpenHelper.NAME,cursor1.getString(1));
            intent.putExtra(UserDataBaseOpenHelper.STOCK_QUANTITY,cursor1.getInt(2));
            intent.putExtra(UserDataBaseOpenHelper.STOCK_QUANTITY_ADVISE,cursor1.getInt(3));
            intent.putExtra(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,cursor1.getInt(4));
        }
        if (cursor2.moveToFirst()) {
            i=i+2;
            intent.putExtra(UserDataBaseOpenHelper.REFERENCE2,cursor2.getString(0));
            intent.putExtra(UserDataBaseOpenHelper.NAME2,cursor2.getString(1));
            intent.putExtra(UserDataBaseOpenHelper.TOTAL_QUANTITY,cursor2.getInt(2));
            intent.putExtra(UserDataBaseOpenHelper.QUANTITY_BORROWED,cursor2.getInt(3));
            intent.putExtra(UserDataBaseOpenHelper.QUANTITY_NOT_BORROWED,cursor2.getInt(4));
            intent.putExtra(UserDataBaseOpenHelper.TOTAL_QUANTITY_ADVISE,cursor2.getInt(5));
            intent.putExtra(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,cursor2.getInt(6));
        }

        cursor1.close();
        cursor2.close();

        if (i==0){
            Intent intent2 = new Intent(context, RefInconnueActivity.class);
            intent2.putExtra(TYPE,"Aucun");
            intent2.putExtra("REFERENCE",ref);
            return intent2;
        }
        if (i==1) intent.putExtra(TYPE,"Stock");
        if (i==2) intent.putExtra(TYPE,"Empruntable");
        if (i==3) intent.putExtra(TYPE,"Double");

        return intent;
    }

    public List<MaterielEnStock> rechercheBDDStock(){

        List<MaterielEnStock> materielList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + UserDataBaseOpenHelper.TABLE_MATERIEL_STOCK;
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
        cursor.close();
        // return contact list
        return materielList;
    }

    public List<MaterielEmpruntable> rechercheBDDEmpruntable(){
        List<MaterielEmpruntable> materielList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + UserDataBaseOpenHelper.TABLE_MATERIEL_LOANABLE;
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
        cursor.close();
        // return contact list
        return materielList;
    }

    public List<Emprunts> rechercheBDDEmprunts(){
        List<Emprunts> empruntsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + UserDataBaseOpenHelper.TABLE_LOAN;
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

        cursor.close();
        // return list
        return empruntsList;
    }

    public void ajouterMateriel(Intent intent, int quantity){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        int quantityStockFuture = intent.getIntExtra("QUANTITE_STOCK",0) + quantity;
        int quantityOrderFuture = intent.getIntExtra("QUANTITE_A_COMMANDER",0) - quantity;

        values.put(UserDataBaseOpenHelper.STOCK_QUANTITY,quantityStockFuture);

        if (quantityOrderFuture > 0){
            values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,quantityOrderFuture);
        }
        else values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,0);

        database.update(UserDataBaseOpenHelper.TABLE_MATERIEL_STOCK, values,
                UserDataBaseOpenHelper.REFERENCE + " LIKE " + intent.getStringExtra("REFERENCE"),
                null);
    }

    public boolean retirerMateriel(Intent intent, int quantity) {

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        int quantityStockFuture = intent.getIntExtra("QUANTITE_STOCK",0) - quantity;

        if (quantityStockFuture >= 0){

            values.put(UserDataBaseOpenHelper.STOCK_QUANTITY,quantityStockFuture);

            if (quantityStockFuture < (intent.getIntExtra("QUANTITE_STOCK_CONSEILLEE",0))){
                values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,(intent.getIntExtra("QUANTITE_STOCK_CONSEILLEE",0)
                        - quantityStockFuture));
            }
            else  values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,0);

            database.update(UserDataBaseOpenHelper.TABLE_MATERIEL_STOCK, values,
                    UserDataBaseOpenHelper.REFERENCE + " LIKE " + intent.getStringExtra("REFERENCE"),
                    null);
            return true;

        }else{
            return false;
        }
    }

    public void ajouterMaterielEmpruntable(Intent intent, int quantity){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        int quantityStockFuture = intent.getIntExtra(UserDataBaseOpenHelper.TOTAL_QUANTITY,0) + quantity;
        int quantityOrderFuture = intent.getIntExtra(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,0) - quantity;
        int quantityNotBorrowedFuture = intent.getIntExtra(UserDataBaseOpenHelper.QUANTITY_NOT_BORROWED,0) + quantity;

        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY,quantityStockFuture);
        values.put(UserDataBaseOpenHelper.QUANTITY_NOT_BORROWED,quantityNotBorrowedFuture);

        if (quantityOrderFuture > 0){
            values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,quantityOrderFuture);
        }
        else values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,0);

        database.update(UserDataBaseOpenHelper.TABLE_MATERIEL_LOANABLE, values,
                UserDataBaseOpenHelper.REFERENCE2 + " LIKE " + intent.getStringExtra(UserDataBaseOpenHelper.REFERENCE2),
                null);
    }

    public boolean retirerMaterielEmpruntable(Intent intent, int quantity) {

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        int quantityStockFuture = intent.getIntExtra(UserDataBaseOpenHelper.TOTAL_QUANTITY,0) - quantity;

        if (quantityStockFuture >= 0){

            values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY,quantityStockFuture);

            if (quantityStockFuture < (intent.getIntExtra(UserDataBaseOpenHelper.TOTAL_QUANTITY_ADVISE,0))){
                values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,(intent.getIntExtra(UserDataBaseOpenHelper.TOTAL_QUANTITY_ADVISE,0)
                        - quantityStockFuture));
            }
            else  values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,0);

            database.update(UserDataBaseOpenHelper.TABLE_MATERIEL_LOANABLE, values,
                    UserDataBaseOpenHelper.REFERENCE2 + " LIKE " + intent.getStringExtra(UserDataBaseOpenHelper.REFERENCE2),
                    null);
            return true;

        }else{
            return false;
        }
    }

    public void ajouterReferenceStock(MaterielEnStock materiel){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseOpenHelper.REFERENCE,materiel.getReference());
        values.put(UserDataBaseOpenHelper.NAME,materiel.getNom());
        values.put(UserDataBaseOpenHelper.STOCK_QUANTITY,materiel.getQuantiteStock());
        values.put(UserDataBaseOpenHelper.STOCK_QUANTITY_ADVISE,materiel.getQuantiteStockConseillee());
        values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER,materiel.getQuantiteACommander());
        database.insert(UserDataBaseOpenHelper.TABLE_MATERIEL_STOCK, null, values);

        database.close();
    }

    public void ajouterReferenceEmpruntable(MaterielEmpruntable materiel){

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseOpenHelper.REFERENCE2,materiel.getReference());
        values.put(UserDataBaseOpenHelper.NAME2,materiel.getNom());
        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY,materiel.getQuantiteTotale());
        values.put(UserDataBaseOpenHelper.QUANTITY_BORROWED,materiel.getQuantiteEmprunte());
        values.put(UserDataBaseOpenHelper.QUANTITY_NOT_BORROWED,materiel.getQuantiteNonEmprunte());
        values.put(UserDataBaseOpenHelper.TOTAL_QUANTITY_ADVISE,materiel.getQuantiteTotaleConseillee());
        values.put(UserDataBaseOpenHelper.QUANTITY_TO_ORDER2,materiel.getQuantiteACommander());
        database.insert(UserDataBaseOpenHelper.TABLE_MATERIEL_LOANABLE, null, values);

        database.close();
    }*/
}
