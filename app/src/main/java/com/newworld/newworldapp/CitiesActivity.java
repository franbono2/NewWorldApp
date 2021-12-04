package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CitiesActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    ListView listView;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        listView = (ListView) findViewById(R.id.listview);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");
        /*if (dbHelper == null) {
            dbHelper = new DbHelper(getApplicationContext());
            SingletonMap.getInstance().put("dbh", dbHelper);
        }*/

        list = dbHelper.getCiudades();

        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        seleccionar_Asentamiento();
    }

    private void seleccionar_Asentamiento(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = list.get(position);
                Toast.makeText(getApplicationContext(),"Asentamiento seleccionado: "+seleccionado,Toast.LENGTH_LONG).show();
                SingletonMap.getInstance().put("asentamiento",seleccionado);
                Intent intento = new Intent(getApplicationContext(), AsentamientoActivity.class);
                startActivity(intento);
            }
        });
    }
}