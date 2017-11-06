package com.appexperts.products.ui.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.appexperts.products.R;
import com.appexperts.products.data.db.DBHelper;
import com.appexperts.products.ui.crud.AddRecord;
import com.appexperts.products.ui.crud.UpdDelRecord;

public class MainActivity extends AppCompatActivity {


    private SimpleCursorAdapter dataAdapter;

/*    private final String
            CLA = "RRS01 MainActivity",
            NO_DATA_MSG = "Wow! Your recipe book is empty. \n\n Add a new recipe.";*/

    public static String TAG_ACTION = "actionToDo";
    public static String ITEM_ID = "itemId";

    // Log
    private final String developer = "RRS";
    private final String TAG = developer + " MainActivity";
    // Database access
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");

        this.dbHelper = new DBHelper(this.getApplicationContext());
        this.db = dbHelper.getWritableDatabase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
        setWidgets();
    }

    private void setWidgets() {
        Log.i(TAG,"setWidgets");
        String[] projection = new String[]{
                DBHelper.PRODUCT_NAME,
                DBHelper.PRODUCT_PRICES
        };

        String[] colsToDisplay = new String[]{
                DBHelper.PRODUCT_NAME,
                DBHelper.PRODUCT_PRICES
        };

        int[] colResIDs = new int[]{
                R.id.productNameView,
                R.id.productPriceView
        };

        Cursor cursor = dbHelper.getAllRecords();
        Log.d(TAG,"Cursor retrieved");

        dataAdapter = new SimpleCursorAdapter(this, R.layout.layout_product_list,cursor, colsToDisplay, colResIDs,0);
        Log.d(TAG,"Simple cursor adapter instantiated");

        final ListView lv = (ListView) findViewById(R.id.lv_products);
        lv.setAdapter(dataAdapter);
        Log.d(TAG,"ListView set");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter,
                                    View myView,
                                    int myItemInt,
                                    long mylng) {
                Cursor c1 = (Cursor) lv.getAdapter().getItem(myItemInt);
                modifyRecord(c1.getInt(0));
//                String title = "col zero: "+c1.getString(0);
//                String message = "col one: "+c1.getString(1);
//                showMessage(title, message);
            }
        });

    }

    private void modifyRecord(int recordID) {
        Log.d(TAG," Selected item: " + recordID);

        Bundle b = new Bundle();
        b.putInt(ITEM_ID,recordID);

        Intent i = new Intent(MainActivity.this, UpdDelRecord.class);
        i.putExtras(b);
        startActivity(i);

    }

    public void onAddBtn(View view){
        Log.d(TAG,"onAddBtn");

        Bundle b = new Bundle();
        b.putInt(TAG_ACTION,1);

        Intent i = new Intent(MainActivity.this, AddRecord.class);
        i.putExtras(b);

        startActivity(i);
    }

    public void onShowBtn(View view) {
        Cursor cursor = dbHelper.getAllRecords();

        if (cursor.getCount() == 0){
            showMessage("No records yet","Try adding some records first.");
            return;
        }

        StringBuilder st = new StringBuilder();
        while (cursor.moveToNext()){
            st.append("id: " + cursor.getString(0) + "\n");
            st.append("name: " + cursor.getString(1) + "\n");
            st.append("Desc: " + cursor.getString(2) + "\n\n");
        }

        showMessage("Data found",st.toString());
    }

    public void  showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
