package com.newworld.newworldapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.newworld.newworldapp.db.DbHelper;
import com.newworld.newworldapp.utils.ToolBar;

import java.util.List;
import java.util.Locale;

public class InfoObjetoActivity extends AppCompatActivity {
    private final ToolBar aux = new ToolBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_objeto);

        DbHelper dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        TextView tv_nombreObjeto = (TextView) findViewById(R.id.tv_nombreObjeto);
        TextView tv_cantidad = (TextView) findViewById(R.id.tv_tipo_value);
        TextView tv_peso = (TextView) findViewById(R.id.tv_lugar_value);
        TextView tv_descripcion = (TextView) findViewById(R.id.tv_descripcion_value);
        TextView tv_origen = (TextView) findViewById(R.id.tv_origen_value);
        TextView tv_categoria = (TextView) findViewById(R.id.tv_categoria_value);
        TextView tv_inventario = (TextView) findViewById(R.id.tv_inventario_value);
        ImageView imagen = (ImageView) findViewById(R.id.imageView);

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
}