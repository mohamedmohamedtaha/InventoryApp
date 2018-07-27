package com.example.manasatpc.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.manasatpc.inventoryapp.data.StoreContract.StoreEntry;

/**
 * Created by ManasatPC on 27/07/18.
 */

public class StoreDPHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = StoreDPHelper.class.getSimpleName();

    //name of the database file
    private static final String DATABASE_NAME = "store.db";
    //database Version .If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public StoreDPHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statement to create the Store table
        String SQL_CREATE_STORE_TABLE = "CREATE TABLE " + StoreEntry.TABLE_NAME + " ("
                + StoreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StoreEntry.PRODUCT_NAME + " TEXT NOT NULL, "
                + StoreEntry.PRICE + " INTEGER NOT NULL, "
                + StoreEntry.QUANTITY + " INTEGER NOT NULL, "
                + StoreEntry.SUPPLIER_NAME + " TEXT, "
                + StoreEntry.SUPPLIER_PHONE_NUMER + " TEXT);";
        //Execute the SQL statement
        db.execSQL(SQL_CREATE_STORE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}