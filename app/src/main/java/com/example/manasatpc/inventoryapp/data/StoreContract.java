package com.example.manasatpc.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by ManasatPC on 27/07/18.
 */

public class StoreContract {

    public StoreContract() {

    }

    /**
     * Inner class that defines constant values for the Store database table.
     * Each entry in the table represents a single item .
     */
    public final static class StoreEntry implements BaseColumns {
        //Name of database table for Store
        public final static String TABLE_NAME = "store";


        //Columns the table

        //id unique
        public final static String _ID = BaseColumns._ID;
        //name product
        public final static String PRODUCT_NAME = "productName";
        //price
        public final static String PRICE = "price";
        //quantity
        public final static String QUANTITY = "quantity";
        //supplierName
        public final static String SUPPLIER_NAME = "supplierName";
        //SupplierPhone Number
        public final static String SUPPLIER_PHONE_NUMER = "supplier_phone_number";

    }
}