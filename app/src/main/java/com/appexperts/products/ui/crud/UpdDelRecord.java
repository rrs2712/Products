package com.appexperts.products.ui.crud;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appexperts.products.R;
import com.appexperts.products.data.db.DBHelper;
import com.appexperts.products.ui.main.MainActivity;

public class UpdDelRecord extends AppCompatActivity {

    // Log
    private final String developer = "RRS";
    private final String TAG = developer + " UpdDelRecord";
    // Widgets
    private EditText productName;
    private EditText productDescription;
    private EditText productPrices;
    private EditText productPhoto;
    private EditText productColors;
    private EditText productStores;
    // Database
    DBHelper db;
    // Business logic
    int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_del_record);

        productName = (EditText) findViewById(R.id.product_name_to_modify);
        productDescription = (EditText) findViewById(R.id.product_description_to_modify);
        productPrices= (EditText) findViewById(R.id.product_prices_to_modify);
        productPhoto= (EditText) findViewById(R.id.product_photo_to_modify);
        productColors = (EditText) findViewById(R.id.product_color_to_modify);
        productStores= (EditText) findViewById(R.id.product_stores_to_modify);

        db = new DBHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        itemID = bundle.getInt(MainActivity.ITEM_ID,0);

        if(itemID<=0){
            Toast.makeText(this,"Invalid item ID",Toast.LENGTH_SHORT).show();
            this.finish();
        }
        Log.d(TAG,"itemID retrieved = " + itemID);

        Cursor cursor = db.getRecord(itemID);
        Log.d(TAG,"Cursor retrieved" );

        Log.d(TAG,"Cursor count = " + cursor.getCount());
        if (cursor.getCount() != 1){
            Toast.makeText(this,"No data to show",Toast.LENGTH_SHORT).show();
            this.finish();
        }

        while (cursor.moveToNext()){
            productName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_NAME)));
            productDescription.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_DESCRIPTION)));
            productPrices.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_PRICES)));
            productPhoto.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_PHOTO)));
            productColors.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLORS)));
            productStores.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_STORES)));
        }
    }

    public void onDelBtn(View view) {
        int del = db.deleteRecord(itemID);

        Log.d(TAG, "record deleted: " + del);
        Toast.makeText(this,"record deleted: " + del,Toast.LENGTH_SHORT).show();

        this.finish();
    }

    public void onUptBtn(View view) {
        boolean isUpdated = db.updateRecord(
            String.valueOf(itemID),
            productName.getText().toString(),
            productDescription.getText().toString(),
            productPrices.getText().toString(),
            productPhoto.getText().toString(),
            productColors.getText().toString(),
            productStores.getText().toString()
        );

        Log.d(TAG, "record updated: " + isUpdated);
        Toast.makeText(this,"record updated",Toast.LENGTH_SHORT).show();

        this.finish();
    }
}
