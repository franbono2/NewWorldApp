package com.newworld.newworldapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;
import com.newworld.newworldapp.utils.ToolBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CitiesActivity extends AppCompatActivity {
    ListView listView;
    List<String> list;
    private final ToolBar aux = new ToolBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        listView = (ListView) findViewById(R.id.listview);
        int idImage = getResources().getIdentifier("background","drawable",getPackageName());
        listView.setBackground(getResources().getDrawable(idImage));


        DbHelper dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        list = dbHelper.getCiudades();

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        seleccionar_Asentamiento();

        Toolbar toolbar = findViewById(R.id.tr_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void seleccionar_Asentamiento(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = list.get(position);
                Resources res = getResources();
                String asent = res.getString(R.string.toast);
                Toast.makeText(getApplicationContext(),asent + " " + seleccionado,Toast.LENGTH_SHORT).show();
                SingletonMap.getInstance().put("asentamiento",seleccionado);
                Intent intento = new Intent(getApplicationContext(), AsentamientoActivity.class);
                startActivity(intento);
            }
        });
    }
}