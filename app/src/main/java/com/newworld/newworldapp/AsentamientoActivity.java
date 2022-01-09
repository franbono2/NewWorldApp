package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;

import java.util.List;

public class AsentamientoActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private String asentamiento;
    private String events_in;
    private String no_events;
    private String inventory_in;
    private String no_objects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asentamiento);

        Resources res = getResources();
        events_in = res.getString(R.string.eventos_in_yes);
        no_events = res.getString(R.string.eventos_in_no);
        inventory_in = res.getString(R.string.objects_yes);
        no_objects = res.getString(R.string.objects_no);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        asentamiento = (String)SingletonMap.getInstance().get("asentamiento");

        TextView textAsentamiento = (TextView) findViewById(R.id.textAsentamiento);
        textAsentamiento.setText(asentamiento);
    }

    public void clickB_Eventos (View view) {
        if(dbHelper.EventosIsNotEmpty(asentamiento)){
            Toast.makeText(getApplicationContext(),events_in + " " + asentamiento,Toast.LENGTH_LONG).show();
            Intent intento = new Intent(this, Eventos_Asentamiento.class);
            startActivity(intento);
        }else{
            Toast.makeText(getApplicationContext(),no_events + " " + asentamiento,Toast.LENGTH_LONG).show();
        }
    }

    public void clickB_Inventario (View view) {
        if(dbHelper.InventariosIsNotEmpty(asentamiento)){
            Toast.makeText(getApplicationContext(),inventory_in + " " + asentamiento,Toast.LENGTH_LONG).show();
            Intent intento = new Intent(this, Inventario_Asentamiento.class);
            startActivity(intento);
        }else{
            Toast.makeText(getApplicationContext(),no_objects + " " + asentamiento,Toast.LENGTH_LONG).show();
        }
    }
}