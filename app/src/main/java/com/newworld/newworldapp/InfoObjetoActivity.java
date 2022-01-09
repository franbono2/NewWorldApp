package com.newworld.newworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;

import java.util.List;
import java.util.Locale;

public class InfoObjetoActivity extends AppCompatActivity {

    private DbHelper dbHelper;
    private TextView tv_nombreObjeto, tv_cantidad, tv_peso, tv_descripcion, tv_origen, tv_categoria, tv_inventario;
    private ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_objeto);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        tv_nombreObjeto = (TextView) findViewById(R.id.tv_nombreObjeto);
        tv_cantidad = (TextView) findViewById(R.id.tv_cantidad_value);
        tv_peso = (TextView) findViewById(R.id.tv_peso_value);
        tv_descripcion = (TextView) findViewById(R.id.tv_descripcion_value);
        tv_origen = (TextView) findViewById(R.id.tv_origen_value);
        tv_categoria = (TextView) findViewById(R.id.tv_categoria_value);
        tv_inventario = (TextView) findViewById(R.id.tv_inventario_value);
        imagen = (ImageView) findViewById(R.id.imageView);

        String nombre,cantidad,peso,descripcion,origen,categoria,inventario,aux;

        nombre = (String)SingletonMap.getInstance().get("objeto");
        tv_nombreObjeto.setText(nombre);

        List<String> attr = dbHelper.getAtrObjeto(nombre);
        cantidad = attr.get(0);
        peso = attr.get(1);
        descripcion = attr.get(2);
        origen = attr.get(3);
        categoria = attr.get(4);
        inventario = (String)SingletonMap.getInstance().get("asentamiento");

        tv_cantidad.setText(cantidad);
        tv_peso.setText(peso);
        tv_descripcion.setText(descripcion);
        tv_origen.setText(origen);
        tv_categoria.setText(categoria);
        tv_inventario.setText(inventario);

        int idImage;
        idImage = getResources().getIdentifier(nombre.toLowerCase(Locale.ROOT).replace(" ",""),"drawable",getPackageName());
        imagen.setImageResource(idImage);
    }
}