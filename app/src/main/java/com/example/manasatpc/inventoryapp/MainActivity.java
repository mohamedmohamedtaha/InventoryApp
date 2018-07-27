package com.example.manasatpc.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manasatpc.inventoryapp.data.StoreContract.StoreEntry;
import com.example.manasatpc.inventoryapp.data.StoreDPHelper;

public class MainActivity extends AppCompatActivity {
    //TextView for show Result
    TextView show_text;
    //Instance for StoreDPHelper
    private StoreDPHelper dpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dpHelper = new StoreDPHelper(this);
        show_text = (TextView) findViewById(R.id.show_text);
        insertData();
        queryData();
    }


    public void insertData() {
        //Create and/or open a database to write from it
        SQLiteDatabase database = dpHelper.getWritableDatabase();

        //Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(StoreEntry.PRODUCT_NAME, "main");
        values.put(StoreEntry.PRICE, "100");
        values.put(StoreEntry.QUANTITY, "10");
        values.put(StoreEntry.SUPPLIER_NAME, "Mohamed");
        values.put(StoreEntry.SUPPLIER_PHONE_NUMER, "0154855265");

        //Insert a new row in the database,  returning the ID of that new row.
        long newRowId = database.insert(StoreEntry.TABLE_NAME, null, values);
        if (newRowId != -1) {
            Toast.makeText(getApplicationContext(), "Insertd Row ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error  ", Toast.LENGTH_LONG).show();

        }


    }

    public void queryData() {
        //Create and/or open a database to read from it
        SQLiteDatabase database = dpHelper.getReadableDatabase();
        //Define a projection that specifies which columns from the database
        //you will actually use after this query
        String[] projection = {StoreEntry._ID, StoreEntry.PRODUCT_NAME
                , StoreEntry.PRICE, StoreEntry.QUANTITY
                , StoreEntry.SUPPLIER_NAME, StoreEntry.SUPPLIER_PHONE_NUMER};

        //Perform a query on the Store table
        Cursor cursor = database.query(StoreEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        try {
            show_text.setText("The store Table contains " + cursor.getCount() + " Store.\n\n");
            show_text.append(StoreEntry._ID + " - " +
                    StoreEntry.PRODUCT_NAME + " - " +
                    StoreEntry.PRICE + " - " +
                    StoreEntry.QUANTITY + " - " +
                    StoreEntry.SUPPLIER_NAME + " - " +
                    StoreEntry.SUPPLIER_PHONE_NUMER + "\n");

            //Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(StoreEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(StoreEntry.PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(StoreEntry.PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(StoreEntry.QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(StoreEntry.SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(StoreEntry.SUPPLIER_PHONE_NUMER);

            //Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                //Use that index to extract the String or Int value of the word
                //at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
                String currentQuantity = cursor.getString(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                show_text.append(("\n" + currentID + " -" + currentProductName + " - " +
                        currentPrice + " - " + currentQuantity + " - " + currentSupplierName + " - "
                        + currentSupplierPhoneNumber));
            }

        } finally {
            //Always close the cursor when you're done reading fromit. This releases all its
            //resources and makes it invalid.
            cursor.close();
        }
    }
}