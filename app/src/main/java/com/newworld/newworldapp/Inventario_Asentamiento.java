package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;

import java.util.List;

public class Inventario_Asentamiento extends AppCompatActivity {

    private DbHelper dbHelper;
    private ListView lv_armas;
    private ListView lv_armaduras;
    private ListView lv_consumibles;
    private List<String> nombre_armas;
    private List<String> nombre_armaduras;
    private List<String> nombre_consumibles;
    static final String ARMAS = "armas";
    static final String ARMADURAS = "armaduras";
    static final String CONSUMIBLES = "consumibles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_asentamiento);

        String asentamiento = (String)SingletonMap.getInstance().get("asentamiento");
        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        lv_armas = findViewById(R.id.LV_Armas);
        lv_armaduras = findViewById(R.id.LV_Armaduras);
        lv_consumibles = findViewById(R.id.LV_Consumibles);

        TextView peso_capacidad = findViewById(R.id.TV_Capacidad);
        int peso = dbHelper.getPeso(asentamiento);
        int capacidad = dbHelper.getCapacidad(asentamiento);
        peso_capacidad.setText("" + peso + " / " + capacidad);

        nombre_armas = dbHelper.getNombreObjetosCategoria(asentamiento, ARMAS);
        nombre_armaduras = dbHelper.getNombreObjetosCategoria(asentamiento, ARMADURAS);
        nombre_consumibles = dbHelper.getNombreObjetosCategoria(asentamiento, CONSUMIBLES);

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_armas);
        lv_armas.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_armaduras);
        lv_armaduras.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_consumibles);
        lv_consumibles.setAdapter(arrayAdapter);
    }
}