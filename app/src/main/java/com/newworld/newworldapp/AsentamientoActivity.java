package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AsentamientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asentamiento);

        String asentamiento = (String)SingletonMap.getInstance().get("asentamiento");

        TextView textAsentamiento = (TextView) findViewById(R.id.textAsentamiento);
        textAsentamiento.setText(asentamiento);
    }
}