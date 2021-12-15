package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;

import java.util.List;

public class AsentamientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asentamiento);

        String asentamiento = (String)SingletonMap.getInstance().get("asentamiento");

        TextView textAsentamiento = (TextView) findViewById(R.id.textAsentamiento);
        textAsentamiento.setText(asentamiento);
    }

    public void clickB_Eventos (View view) {
        Intent intento = new Intent(this, Eventos_Asentamiento.class);
        startActivity(intento);
    }

    public void clickB_Inventario (View view) {
        //Intent intento = new Intent(this, CitiesActivity.class);
        //startActivity(intento);
    }
}