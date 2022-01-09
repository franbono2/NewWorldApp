package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class InfoEventoActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private TextView tv_nombreEvento, tv_tipo, tv_lugar;
    private ImageView imagen;
    private CalendarView calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_evento);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        tv_nombreEvento = (TextView) findViewById(R.id.tv_nombreObjeto);
        tv_tipo = (TextView) findViewById(R.id.tv_cantidad_value);
        tv_lugar = (TextView) findViewById(R.id.tv_peso_value);
        imagen = (ImageView) findViewById(R.id.imageView);
        calendario = (CalendarView) findViewById(R.id.calendarView);

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

    }
}