package com.newworld.newworldapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;
import com.newworld.newworldapp.utils.ToolBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class InfoEventoActivity extends AppCompatActivity {

    private final ToolBar aux = new ToolBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_evento);

        DbHelper dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        TextView tv_nombreEvento = (TextView) findViewById(R.id.tv_nombreObjeto);
        TextView tv_tipo = (TextView) findViewById(R.id.tv_cantidad_value);
        TextView tv_lugar = (TextView) findViewById(R.id.tv_peso_value);
        ImageView imagen = (ImageView) findViewById(R.id.imageView);
        CalendarView calendario = (CalendarView) findViewById(R.id.calendarView);

        String nombre,fecha,tipo,lugar;
        nombre = (String)SingletonMap.getInstance().get("evento");
        tv_nombreEvento.setText(nombre);

        List<String> attr = dbHelper.getAtrEvento(nombre);
        tipo = attr.get(0);
        fecha = attr.get(1);
        lugar = (String)SingletonMap.getInstance().get("asentamiento");

        tv_lugar.setText(lugar);

        tv_tipo.setText(tipo);
        int idImage;
        idImage = getResources().getIdentifier(tipo.toLowerCase(Locale.ROOT),"drawable",getPackageName());
        imagen.setImageResource(idImage);

        try {
            calendario.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(fecha).getTime(),true,true);
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
            case R.id.cIdioma: aux.cambiarIdioma(this); break;
        }
        return super.onOptionsItemSelected(item);
    }
}