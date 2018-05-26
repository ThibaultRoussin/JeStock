package fr.epf.jestock.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Thibault on 27/04/2018.
 */

public class UserDataBaseOpenHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JeStock";
    public static final String TABLE_USERS = "Users";
    public static final String TABLE_MATERIEL_STOCK = "Materiel_Stock";
    public static final String TABLE_MATERIEL_LOANABLE = "Materiel_Empruntable";
    public static final String TABLE_LOAN = "Emprunts";
    public static final String TABLE_REQUEST = "Requetes";

    // Colonnes Users
    public static final String KEY_ID = "id";
    public static final String LOGIN = "IDENTIFIANT";
    public static final String PASSWORD = "MDP";
    public static final String EMAIL = "EMAIL";
    public static final String RIGHT = "DROIT";

    // Colonnes Materiel_Stock
    public static final String REFERENCE = "REFERENCE";
    public static final String NAME = "NOM";
    public static final String STOCK_QUANTITY = "QUANTITE_STOCK";
    public static final String STOCK_QUANTITY_ADVISE = "QUANTITE_STOCK_CONSEILLEE";
    public static final String QUANTITY_TO_ORDER = "QUANTITE_A_COMMANDER";

    // Colonnes Materiel_Empruntable
    public static final String REFERENCE2 = "REFERENCE";
    public static final String NAME2 = "NOM";
    public static final String TOTAL_QUANTITY = "QUANTITE_TOTALE";
    public static final String QUANTITY_BORROWED = "QUANTITE_EMPRUNTEE";
    public static final String QUANTITY_NOT_BORROWED = "QUANTITE_NON_EMPRUNTEE";
    public static final String TOTAL_QUANTITY_ADVISE = "QUANTITE_TOTALE_CONSEILLEE";
    public static final String QUANTITY_TO_ORDER2 = "QUANTITE_A_COMMANDER";

    // Colonnes Emprunts
    public static final String KEY_ID2 = "id";
    public static final String MATERIEL_NAME = "NOM_MATERIEL";
    public static final String MATERIEL_REFERENCE = "REFERENCE_MATERIEL";
    public static final String BORROWER_TYPE = "TYPE_EMPRUNTEUR";
    public static final String BORROWER_FIRST_NAME = "PRENOM_EMPRUNTEUR";
    public static final String BORROWER_NAME = "NOM_EMPRUNTEUR";
    public static final String BORROWER_EMAIL = "EMAIL_EMPRUNTEUR";
    public static final String LOAN_DATE = "DATE_EMPRUNT";
    public static final String RENDER_DATE = "DATE_RENDU";

    //Colonnes Requetes
    public static final String KEY_ID3 = "id";
    public static final String REQUEST = "REQUETE";

    public UserDataBaseOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("TableUsers", "onCreate: ");
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + LOGIN + " TEXT," + PASSWORD + " TEXT," + EMAIL + " TEXT," + RIGHT + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        Log.d("TableMaterielSotck", "onCreate: ");
        String CREATE_MATERIEL_STOCK_TABLE = "CREATE TABLE " + TABLE_MATERIEL_STOCK + "("
                + REFERENCE + " PRIMARY KEY," + NAME + " TEXT," + STOCK_QUANTITY + " INT,"
                + STOCK_QUANTITY_ADVISE + " INT," + QUANTITY_TO_ORDER + " INT" + ")";
        db.execSQL(CREATE_MATERIEL_STOCK_TABLE);

        String CREATE_MATERIEL_LOANABLE_TABLE = "CREATE TABLE " + TABLE_MATERIEL_LOANABLE + "("
                + REFERENCE2 + " PRIMARY KEY," + NAME2 + " TEXT," + TOTAL_QUANTITY + " INT," + QUANTITY_BORROWED + " INT,"
                + QUANTITY_NOT_BORROWED + " INT," + TOTAL_QUANTITY_ADVISE + " INT," + QUANTITY_TO_ORDER2 + " INT" +")";
        db.execSQL(CREATE_MATERIEL_LOANABLE_TABLE);

        String CREATE_LOAN_TABLE = "CREATE TABLE " + TABLE_LOAN + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY," + MATERIEL_NAME + " TEXT," + MATERIEL_REFERENCE + " TEXT," + BORROWER_TYPE + " TEXT,"
                + BORROWER_FIRST_NAME + " TEXT," + BORROWER_NAME + " TEXT," + BORROWER_EMAIL + " TEXT,"
                + LOAN_DATE + " TEXT," + RENDER_DATE + " TEXT" + ")";
        db.execSQL(CREATE_LOAN_TABLE);

        String CREATE_REQUEST_TABLE = "CREATE TABLE " + TABLE_REQUEST + "("
                + KEY_ID3 + " INTEGER PRIMARY KEY," + REQUEST + " TEXT" + ")";
        db.execSQL(CREATE_REQUEST_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
