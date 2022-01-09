package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        seleccionar_Evento();
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