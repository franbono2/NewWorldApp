package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

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

    private static final String CITY_NAMES[] = {
            "Primera Luz",
            "Cayos de Alfanje",
            "Guardaelviento",
            "Riscos del Monarca",
            "Aguas Fétidas",
            "Ocaso",
            "Costa de la Zozobra",
            "Altos de Escamanegra",
            "Ciénaga de los Tejedores",
            "Bosque Luminoso",
            "Valle del Pesar",
    };

    private static final String[] KEYS = {
            "Inventario",
            "Eventos",
    };

    private static final Articulo[] ARTICULOS = {
            new Articulo("Armadura", "Amuleto", "Amuleto del hechizado del barbaro"),
            new Articulo("Armadura", "Pecho", "Armadura de placas del aventurero"),
            new Articulo("Arma", "Martillo", "Mazo abominable"),
            new Articulo("Recurso", "Recurso", "Vaso abandonado"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        if (dbHelper == null) {
            dbHelper = new DbHelper(getApplicationContext());
            SingletonMap.getInstance().put("dbh", dbHelper);
        }

    }

    public void botonPulsado (View view) {
        Intent intento = new Intent(this, CitiesActivity.class);
        startActivity(intento);
    }
}