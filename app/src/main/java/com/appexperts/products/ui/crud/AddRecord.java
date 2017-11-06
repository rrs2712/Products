package com.appexperts.products.ui.crud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.appexperts.products.R;
import com.appexperts.products.data.db.DBHelper;
import com.appexperts.products.data.model.Product;

public class AddRecord extends AppCompatActivity {

    // Log
    private final String developer = "RRS";
    private final String TAG = developer + " AddRecord";
    // Widgets
    private EditText productName;
    private EditText productDescription;
    private EditText productPrices;
    private EditText productPhoto;
    private EditText productColors;
    private EditText productStores;
    // Database
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        productName = (EditText) findViewById(R.id.product_name);
        productDescription = (EditText) findViewById(R.id.product_description);
        productPrices = (EditText) findViewById(R.id.product_prices);
        productPhoto = (EditText) findViewById(R.id.product_photo);
        productColors = (EditText) findViewById(R.id.product_colors);
        productStores = (EditText) findViewById(R.id.product_stores);

        db = new DBHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Product fkProduct = Product.getFakeProduct();

        productName.setText(fkProduct.getName());
        productDescription.setText(fkProduct.getDescription());
        productPrices.setText(fkProduct.getPrices());
        productPhoto.setText(fkProduct.getPhoto());
        productColors.setText(fkProduct.getColors());
        productStores.setText(fkProduct.getStores());
    }

    public void onSaveBtn(View view) {
        boolean isSaved = db.saveRecord(
            productName.getText().toString(),
            productDescription.getText().toString(),
            productPrices.getText().toString(),
            productPhoto.getText().toString(),
            productColors.getText().toString(),
            productStores.getText().toString()
        );

        Log.d(TAG, "record saved: " + isSaved);
        Toast.makeText(this,"record saved",Toast.LENGTH_SHORT).show();

        this.finish();
    }
}
