package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.newworld.newworldapp.db.DbHelper;

import java.util.List;

public class Eventos_Asentamiento extends AppCompatActivity {

    private DbHelper dbHelper;
    private ListView lv_eventos;
    private List<String> nombreEventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_asentamiento);

        String asentamiento = (String)SingletonMap.getInstance().get("asentamiento");

        lv_eventos = findViewById(R.id.LV_EventosAsentmiento);
        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        nombreEventos = dbHelper.getEventosAsentamientoJoin(asentamiento);

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreEventos);
        lv_eventos.setAdapter(arrayAdapter);
    }
}