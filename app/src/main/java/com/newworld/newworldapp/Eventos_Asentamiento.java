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
import android.widget.ListView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;
import com.newworld.newworldapp.utils.ToolBar;

import java.util.List;

public class Eventos_Asentamiento extends AppCompatActivity {

    private ListView lv_eventos;
    private List<String> nombreEventos;
    private final ToolBar aux = new ToolBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_asentamiento);

        String asentamiento = (String)SingletonMap.getInstance().get("asentamiento");

        lv_eventos = findViewById(R.id.LV_EventosAsentmiento);
        DbHelper dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        nombreEventos = dbHelper.getEventosAsentamientoJoin(asentamiento);

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreEventos);
        lv_eventos.setAdapter(arrayAdapter);
        seleccionar_Evento();

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

    private void seleccionar_Evento(){
        lv_eventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Resources res = getResources();
            String event_sel = res.getString(R.string.event_sel);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = nombreEventos.get(position);
                Toast.makeText(getApplicationContext(),event_sel+ " " +seleccionado,Toast.LENGTH_LONG).show();
                SingletonMap.getInstance().put("evento",seleccionado);
                Intent intento = new Intent(getApplicationContext(), InfoEventoActivity.class);
                startActivity(intento);
            }
        });
    }
}