package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    static final String ARMAS = "Armas";
    static final String ARMADURAS = "Armaduras";
    static final String CONSUMIBLES = "Consumibles";

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

        seleccionar_Objeto();
    }

    private void seleccionar_Objeto(){
        lv_armas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombre_armas.get(position);
                Toast.makeText(getApplicationContext(),"Objeto seleccionado: "+seleccionado,Toast.LENGTH_LONG).show();
                SingletonMap.getInstance().put("objeto",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoObjetoActivity.class);
                startActivity(intento);
            }
        });

        lv_armaduras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombre_armaduras.get(position);
                Toast.makeText(getApplicationContext(),"Objeto seleccionado: "+seleccionado,Toast.LENGTH_LONG).show();
                SingletonMap.getInstance().put("objeto",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoObjetoActivity.class);
                startActivity(intento);
            }
        });

        lv_consumibles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombre_consumibles.get(position);
                Toast.makeText(getApplicationContext(),"Objeto seleccionado: "+seleccionado,Toast.LENGTH_LONG).show();
                SingletonMap.getInstance().put("objeto",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoObjetoActivity.class);
                startActivity(intento);
            }
        });
    }
}