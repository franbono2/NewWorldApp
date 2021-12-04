package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        if (dbHelper == null) {
            dbHelper = new DbHelper(getApplicationContext());
            SingletonMap.getInstance().put("dbh", dbHelper);
        }
        dbHelper.getReadableDatabase(); //Usamos este comando para crear la base de datos en el caso de que no exista

    }

    public void botonPulsado (View view) {
        Intent intento = new Intent(this, CitiesActivity.class);
        startActivity(intento);
    }
}