package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CITY_NAMES[] = {
            "Primera Luz",
            "Cayos de Alfanje",
            "Guardaelviento",
            "Riscos del Monarca",
            "Aguas Fétidas",
            "Ocaso",
            "Costa de la Zozobra",
            "Altos de Escamanegra",
            "Ciénaga de los Tejedores",
            "Bosque Luminoso",
            "Valle del Pesar",
    };

    private static final String[] KEYS = {
            "Inventario",
            "Eventos",
    };

    private static final Articulo[] ARTICULOS = {
            new Articulo("Armadura", "Amuleto", "Amuleto del hechizado del barbaro"),
            new Articulo("Armadura", "Pecho", "Armadura de placas del aventurero"),
            new Articulo("Arma", "Martillo", "Mazo abominable"),
            new Articulo("Recurso", "Recurso", "Vaso abandonado"),
    };

    private SingletonMap singletonMap;
    private HashMap<String, HashMap<String, List<Object>>> map;
    private HashMap<String, List<Object>> mapAux;

    private void initDict() {
        if(singletonMap == null){
            map = new HashMap<String, HashMap<String, List<Object>>>();
            mapAux = new HashMap<String, List<Object>>();
            //Articulos al inventario
            mapAux.put(KEYS[0], Arrays.asList(ARTICULOS));
            //Guerras e invasiones a eventos
            //mapAux.put(KEYS[1], Arrays.asList(EVENTOS));
            //Por cada ciudad añado el map de eventos e inventario
            //En este caso he puesto todas las ciudades con lo mismo
            for (int i = 0; i < CITY_NAMES.length; i++){
                map.put(CITY_NAMES[i], mapAux);
            }
            singletonMap = SingletonMap.getInstance(map);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDict();
    }

    public void botonPulsado (View view) {
        Intent intento = new Intent(this, CitiesActivity.class);
        startActivity(intento);
    }
}