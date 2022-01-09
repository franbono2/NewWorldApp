package com.newworld.newworldapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.main));

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        if (dbHelper == null) {
            dbHelper = new DbHelper(getApplicationContext());
            SingletonMap.getInstance().put("dbh", dbHelper);
        }
        dbHelper.getReadableDatabase(); //Usamos este comando para crear la base de datos en el caso de que no exista

        //english
        setAppLocale("en");
        

    }

    private void setAppLocale(String localeCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localeCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    public void botonPulsado (View view) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        Resources res = getResources();
        String alertamsg = res.getString(R.string.alerta);
        String positive = res.getString(R.string.postitiva);
        String negative = res.getString(R.string.negativa);
        String ciudades = res.getString(R.string.ciudad);
        alerta.setMessage(alertamsg)
                .setCancelable(false)
                .setPositiveButton( positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intento = new Intent(MainActivity.this, CitiesActivity.class);
                        startActivity(intento);
                    }
                })
                .setNegativeButton( negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle(ciudades);
        titulo.show();
    }
}