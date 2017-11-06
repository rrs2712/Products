package com.appexperts.products.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rrs27 on 2017-11-04.
 */

public class DBHelper extends SQLiteOpenHelper{

    // Log
    private final String developer = "RRS";
    private final String TAG = developer + " DBHelper";
    // DB
    public static final String DB_NAME = "ProductDB";
    public static final int DB_VERSION = 1;
    private static SQLiteDatabase.CursorFactory DB_FACTORY = null;
    // Product fields
    public static final String TABLE_PRODUCT = "Product";
    public static final String PRODUCT_ID = "_id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_PRICES = "prices";
    public static final String PRODUCT_PHOTO = "photo";
    public static final String PRODUCT_COLORS = "colors";
    public static final String PRODUCT_STORES = "stores";
    // Product table
    private final String DROP_TABLE_PRODUCT = "DROP TABLE IF EXISTS " + TABLE_PRODUCT;
    private final String CREATE_TABLE_PRODUCT = "CREATE TABLE IF NOT EXISTS " +
        TABLE_PRODUCT + "(" +
        PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        PRODUCT_NAME + " TEXT(50), " +
        PRODUCT_DESCRIPTION + " TEXT(2000), " +
        PRODUCT_PRICES + " TEXT(500), " +
        PRODUCT_PHOTO + " TEXT(500), " +
        PRODUCT_COLORS + " TEXT(500), " +
        PRODUCT_STORES + " TEXT(2000));";
    private final String INITIAL_RECORDS =
        "INSERT INTO Product (name, description, prices, photo, colors, stores) " +
        "VALUES('Coca Cola', 'Soda', '{p1:1,p2:1.5}', '', '{1:black,2:red}', '{1:{name:addess},2:{name:address}}'); " +
        "INSERT INTO Product (name, description, prices, photo, colors, stores) " +
        "VALUES('Pepsi', 'soft drink', '{p1:0.5,p2:1}', '', '{1:black,2:blue}', '{1:{name:addess},2:{name:address}}'); " +
        "INSERT INTO Product (name, description, prices, photo, colors, stores) " +
        "VALUES('Cream soda', 'Soda', '{p1:1,p2:2}', '', '{1:white,2:blue}', '{1:{name:addess},2:{name:address}}');";
    // Raw queries
    private String SELECT_ALL_PRODUCTS =
        "SELECT " +
            PRODUCT_ID + "," +
            PRODUCT_NAME + "," +
            PRODUCT_DESCRIPTION + "," +
            PRODUCT_PRICES + "," +
            PRODUCT_PHOTO + "," +
            PRODUCT_COLORS + "," +
            PRODUCT_STORES +
        " FROM " +
            TABLE_PRODUCT + ";";
    private String SELECT_ONE_PRODUCT =
            "SELECT " +
                PRODUCT_ID + "," +
                PRODUCT_NAME + "," +
                PRODUCT_DESCRIPTION + "," +
                PRODUCT_PRICES + "," +
                PRODUCT_PHOTO + "," +
                PRODUCT_COLORS + "," +
                PRODUCT_STORES +
            " FROM " +
                TABLE_PRODUCT + " WHERE _id = ?";
    private String DELETE_ONE_RECORD = "DELETE FROM Product WHERE _Id = ?";
    // ## Constructors ## //

    public DBHelper(Context context) {
        //super(context, name, factory, version);

        super(context, DB_NAME, DB_FACTORY, DB_VERSION);
        Log.d(TAG,"DBHelper instantiated");
    }

    // ## SQLiteOpenHelper extended-method's implementation ## //

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");

        db.execSQL(CREATE_TABLE_PRODUCT);
//        db.execSQL(INITIAL_RECORDS);
        Log.d(TAG, TABLE_PRODUCT + " table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade, from version " + oldVersion + " to " + newVersion);

        db.execSQL(DROP_TABLE_PRODUCT);
        Log.d(TAG, TABLE_PRODUCT + " table dropped");

        onCreate(db);
    }

    public boolean saveRecord(String name, String desc, String prices, String photo, String colors, String stores){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME,name);
        cv.put(PRODUCT_DESCRIPTION,desc);
        cv.put(PRODUCT_PRICES,prices);
        cv.put(PRODUCT_PHOTO,photo);
        cv.put(PRODUCT_COLORS,colors);
        cv.put(PRODUCT_STORES,stores);

        long result = db.insert(TABLE_PRODUCT,null, cv);
        return (result == -1) ? false : true;
    }

    public Cursor getAllRecords(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery(SELECT_ALL_PRODUCTS, null);
    }

    public Cursor getRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sID = String.valueOf(id);
        return db.rawQuery(SELECT_ONE_PRODUCT, new String[]{sID});
    }

    public int deleteRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sID = String.valueOf(id);
        return db.rawQuery(DELETE_ONE_RECORD, new String[]{sID}).getCount();
    }

    public boolean updateRecord(String id, String name, String desc, String prices, String photo, String colors, String stores){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_ID,id);
        cv.put(PRODUCT_NAME,name);
        cv.put(PRODUCT_DESCRIPTION,desc);
        cv.put(PRODUCT_PRICES,prices);
        cv.put(PRODUCT_PHOTO,photo);
        cv.put(PRODUCT_COLORS,colors);
        cv.put(PRODUCT_STORES,stores);

        int result = db.update(TABLE_PRODUCT,cv,"_id = ?", new String[]{id});
        return (result == 1) ? true : false;
    }
}
