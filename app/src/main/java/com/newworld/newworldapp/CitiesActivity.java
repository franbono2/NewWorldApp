package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.newworld.newworldapp.db.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CitiesActivity extends AppCompatActivity {
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        ListView listView = (ListView) findViewById(R.id.listview);
        List<String> list;

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        /*if (dbHelper == null) {
            dbHelper = new DbHelper(getApplicationContext());
            SingletonMap.getInstance().put("dbh", dbHelper);
        }*/

        list = dbHelper.getCiudades();

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
    }
}