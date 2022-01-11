package com.newworld.newworldapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;
import com.newworld.newworldapp.utils.ToolBar;

import java.util.LinkedList;
import java.util.List;

public class Inventario_Asentamiento extends AppCompatActivity {

    private ListView lv_armas;
    private ListView lv_armaduras;
    private ListView lv_consumibles;
    private List<String> nombre_armas = new LinkedList<>();
    private List<String> nombre_armaduras = new LinkedList<>();
    private List<String> nombre_consumibles = new LinkedList<>();
    private final ToolBar aux = new ToolBar();
    private String object_sel;
    static final String ARMAS = "Armas";
    static final String ARMADURAS = "Armaduras";
    static final String CONSUMIBLES = "Consumibles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_asentamiento);

        String asentamiento = (String)SingletonMap.getInstance().get("asentamiento");
        DbHelper dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        lv_armas = findViewById(R.id.LV_Armas);
        lv_armaduras = findViewById(R.id.LV_Armaduras);
        lv_consumibles = findViewById(R.id.LV_Consumibles);

        TextView peso_capacidad = findViewById(R.id.TV_Capacidad);
        int peso = dbHelper.getPeso(asentamiento);
        int capacidad = dbHelper.getCapacidad(asentamiento);
        peso_capacidad.setText("" + peso + " / " + capacidad);

        Resources res = getResources();
        object_sel = res.getString(R.string.object_sel);

        if(dbHelper.ObjectsIsNotEmpty(asentamiento, ARMAS)){
            nombre_armas = dbHelper.getNombreObjetosCategoria(asentamiento, ARMAS);
        }else {
            TextView tarmas = findViewById(R.id.TV_Armas);
            ImageView imgArma = findViewById(R.id.IV_Arma);
            tarmas.setVisibility(View.GONE);
            lv_armas.setVisibility(View.GONE);
            imgArma.setVisibility(View.GONE);
        }
        if(dbHelper.ObjectsIsNotEmpty(asentamiento, ARMADURAS)){
            nombre_armaduras = dbHelper.getNombreObjetosCategoria(asentamiento, ARMADURAS);
        }else{
            TextView tarmaduras = findViewById(R.id.TV_Armaduras);
            ImageView imgArmadura = findViewById(R.id.IV_Armadura);
            tarmaduras.setVisibility(View.GONE);
            lv_armaduras.setVisibility(View.GONE);
            imgArmadura.setVisibility(View.GONE);
        }
        if(dbHelper.ObjectsIsNotEmpty(asentamiento, CONSUMIBLES)){
            nombre_consumibles = dbHelper.getNombreObjetosCategoria(asentamiento, CONSUMIBLES);
        }else{
            TextView tconsumibles = findViewById(R.id.TV_Consumibles);
            ImageView imgPoti = findViewById(R.id.IV_Poti);
            tconsumibles.setVisibility(View.GONE);
            lv_consumibles.setVisibility(View.GONE);
            imgPoti.setVisibility(View.GONE);
        }

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_armas);
        lv_armas.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_armaduras);
        lv_armaduras.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombre_consumibles);
        lv_consumibles.setAdapter(arrayAdapter);

        seleccionar_Objeto();

        Toolbar toolbar = findViewById(R.id.tr_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.english: aux.cambiarIdioma(this,"en","Selected language: english");
                break;
            case R.id.spain: aux.cambiarIdioma(this,"es","Idioma seleccionado: español");
                break;
            //Idioma español por defecto
            default: aux.cambiarIdioma(this,"es","Idioma seleccionado: español");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void seleccionar_Objeto(){
        lv_armas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombre_armas.get(position);
                Toast.makeText(getApplicationContext(),object_sel+ " " +seleccionado,Toast.LENGTH_SHORT).show();
                SingletonMap.getInstance().put("objeto",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoObjetoActivity.class);
                startActivity(intento);
            }
        });

        lv_armaduras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombre_armaduras.get(position);
                Toast.makeText(getApplicationContext(),object_sel+ " "+seleccionado,Toast.LENGTH_SHORT).show();
                SingletonMap.getInstance().put("objeto",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoObjetoActivity.class);
                startActivity(intento);
            }
        });

        lv_consumibles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombre_consumibles.get(position);
                Toast.makeText(getApplicationContext(),object_sel+ " " +seleccionado,Toast.LENGTH_SHORT).show();
                SingletonMap.getInstance().put("objeto",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoObjetoActivity.class);
                startActivity(intento);
            }
        });
    }
}