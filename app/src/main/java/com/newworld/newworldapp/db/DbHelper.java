package com.newworld.newworldapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "BDNW.db";
    private static final String DB_PATH = "/data/data/com.newworld.newworldapp/databases/";
    private static final String TABLE_EVENTO = "CREATE TABLE IF NOT EXISTS t_evento (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "tipo TEXT," +
            "fecha TEXT," +
            "id_asentamiento INTEGER," +
            "FOREIGN KEY(id_asentamiento) REFERENCES t_asentamiento(id))";
    private static final String TABLE_ASENTAMIENTO = "CREATE TABLE IF NOT EXISTS t_asentamiento (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre INTEGER NOT NULL," +
            "tipo TEXT," +
            "posada INTEGER," +
            "id_inventario INTEGER," +
            "FOREIGN KEY(id_inventario) REFERENCES t_inventario(id))";
    private static final String TABLE_INVENTARIO = "CREATE TABLE IF NOT EXISTS t_inventario (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "capacidad INTEGER," +
            "peso_acumulado INTEGER)";
    private static final String TABLE_OBJETO = "CREATE TABLE IF NOT EXISTS t_objeto (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT NOT NULL," +
            "cantidad INTEGER," +
            "peso INTEGER," +
            "descripcion TEXT," +
            "origen TEXT," +
            "categoria TEXT," +
            "id_inventario INTEGER," +
            "FOREIGN KEY(id_inventario) REFERENCES t_inventario(id))";

    private static final String INSERT_EVENTO = "INSERT INTO t_evento VALUES (0,'Invasión en Aguas Fétidas','invasion','27/11/21',0);";
    private static final String INSERT_ASENTAMIENTO = "INSERT INTO t_asentamiento VALUES (0,'Aguas Fétidas','pueblo',1,0)," +
            " (1,'Bosque Luminoso','ciudad',1,1)," +
            " (2,'Cayos del Alfanje','villa',1,2)," +
            " (3,'Ciénaga de los Tejedores','aldea',1,3)," +
            " (4,'Costa de la Zozobra','villa',1,4)," +
            " (5,'Ocaso','ciudad',1,5)," +
            " (6,'Guadalviento','villa',1,6)," +
            " (7,'Altos de Escamanegra','capital',1,7)," +
            " (8,'Primera Luz','pueblo',1,8)," +
            " (9,'Riscos del Monarca','aldea',1,9)," +
            " (10,'Valle del Pesar','ciudad',1,10);";
    private static final String INSERT_INVENTARIO = "INSERT INTO t_inventario VALUES (0,200,150)," +
            " (1,250,100)," +
            " (2,220,175)," +
            " (3,270,140)," +
            " (4,290,200)," +
            " (5,300,150)," +
            " (6,180,60)," +
            " (7,190,110)," +
            " (8,200,170)," +
            " (9,220,130)," +
            " (10,210,100);";
    private static final String INSERT_OBJETO = "INSERT INTO t_objeto VALUES (0,'Poción de curación',20,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',0);";

    private SQLiteDatabase db;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_EVENTO);
        db.execSQL(TABLE_ASENTAMIENTO);
        db.execSQL(TABLE_INVENTARIO);
        db.execSQL(TABLE_OBJETO);

        db.execSQL(INSERT_EVENTO);
        db.execSQL(INSERT_ASENTAMIENTO);
        db.execSQL(INSERT_INVENTARIO);
        db.execSQL(INSERT_OBJETO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS t_evento;");
        db.execSQL("DROP TABLE IF EXISTS t_asentamento;");
        db.execSQL("DROP TABLE IF EXISTS t_inventario;");
        db.execSQL("DROP TABLE IF EXISTS t_objeto;");
        onCreate(db);
    }

    public void openDB() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void closeDB(){
        if(db != null){
            db.close();
        }
        super.close();
    }

    public List<String> getCiudades(){
        List<String> list = new ArrayList<>();
        openDB();
        db = getReadableDatabase();
        if(db != null){
            Cursor c = db.rawQuery("SELECT nombre FROM t_asentamiento ORDER BY nombre ASC", null);

            if(c != null){
                c.moveToFirst();
                do{
                    String nombre = c.getString(0);
                    list.add(nombre);
                }while(c.moveToNext());
            }
        }
        closeDB();
        return list;
    }

    public List<String> getEventosAsentamiento(String asentamiento) {
        List<String> list = new ArrayList<>();
        openDB();
        db = getReadableDatabase();
        if(db != null){
            int idAsentamiento = getIdAsentamiento(asentamiento);
            String[] selectionArgs = {Integer.toString(idAsentamiento)};
            //NOT WORKING
            Cursor c = db.rawQuery("SELECT nombre FROM t_evento WHERE id_asentamiento = ?", selectionArgs);
            if(c != null){
                c.moveToFirst();
                do{
                    String nombre = c.getString(0);
                    list.add(nombre);
                }while(c.moveToNext());
            }
        }
        closeDB();
        return list;
    }

    private int getIdAsentamiento(String asentamiento) {
        int idAsentamiento = -1;
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento};
            Cursor c = db.rawQuery("SELECT id FROM t_asentamiento WHERE nombre = ?", selectionArgs);
            if(c != null){
                c.moveToFirst();
                idAsentamiento = c.getInt(0);
            }
        }
        closeDB();
        return idAsentamiento;
    }
}
