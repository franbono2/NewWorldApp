package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CitiesActivity extends AppCompatActivity {
    private SingletonMap singletonMap;
    private HashMap<String, HashMap<String, List<Object>>> map;

    private void initDict(){
        //La instancia ya deberia existir y se nos devolveria el singleton no vacio
        map = new HashMap<String, HashMap<String, List<Object>>>();
        singletonMap = SingletonMap.getInstance(map);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initDict();
        //ListView listView = new ListView(this);
        ListView listView = (ListView) findViewById(R.id.listview);
        List<String> list = new ArrayList<String>();
        Set<String> keys = singletonMap.map.keySet();
        for(String ciudad : keys){
            list.add(ciudad);
        }
        //list.add("Ocaso");
        //list.add("Primera Luz");
        //list.add("Bosque Luminoso");
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
    }
}