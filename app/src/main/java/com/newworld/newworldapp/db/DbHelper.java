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
            "nombre TEXT NOT NULL," +
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

    private static final String INSERT_EVENTO = "INSERT INTO t_evento VALUES (0,'Invasión en Aguas Fétidas','Invasion','27/11/2021',0)," +
            " (1,'Guerra en Aguas Fétidas','Guerra','27/11/2021',0);";

    private static final String INSERT_ASENTAMIENTO = "INSERT INTO t_asentamiento VALUES (0,'Aguas Fétidas','pueblo',1,0)," +
            " (1,'Bosque Luminoso','ciudad',1,1)," +
            " (2,'Cayos del Alfanje','villa',1,2)," +
            " (3,'Ciénaga de los Tejedores','aldea',1,3)," +
            " (4,'Costa de la Zozobra','villa',1,4)," +
            " (5,'Ocaso','ciudad',1,5)," +
            " (6,'Guadaelviento','villa',1,6)," +
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
    private static final String INSERT_OBJETO = "INSERT INTO t_objeto VALUES (0,'Pocion de salud',20,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',0)," +
            " (1,'Abretormentas',1,10,'El sol asomó entre las nubes con la promesa de un futuro mejor','Enemigos','Armas', 0)," +
            " (2,'Abrigo de aventurero',1,6,'Un abrigo bien pertrechado, te servira igual de bien que a su antigo dueño','Enemigos','Armaduras', 0)," +
            " (3,'Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',0)," +
            " (4,'Racion ligera',20,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',0);";

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

    public List<String> getEventosAsentamientoJoin(String asentamiento) {
        List<String> list = new ArrayList<>();
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento};
            Cursor c = db.rawQuery("SELECT t_evento.nombre FROM t_evento INNER JOIN t_asentamiento ON t_evento.id_asentamiento = t_asentamiento.id WHERE t_asentamiento.nombre = ?", selectionArgs);
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

    public boolean EventosIsNotEmpty(String asentamiento) {
        boolean exist = false;
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento};
            Cursor c = db.rawQuery("SELECT t_evento.nombre FROM t_evento INNER JOIN t_asentamiento ON t_evento.id_asentamiento = t_asentamiento.id WHERE t_asentamiento.nombre = ?", selectionArgs);
            if(c != null){
                exist = c.moveToFirst();
            }
        }
        closeDB();
        return exist;
    }


    //devuelve tipo, fecha, id_asentamiento de un evento
    public List<String> getAtrEvento(String nombre) {
        List<String> res = new ArrayList<>();
        openDB();
        db = getReadableDatabase();
        if (db != null) {
            String[] selectionArgs = {nombre};
            Cursor c = db.rawQuery("SELECT tipo, fecha, id_asentamiento FROM t_evento WHERE nombre = ?", selectionArgs);
            if (c != null) {
                c.moveToFirst();
                res.add(c.getString(0));
                res.add(c.getString(1));
                res.add(c.getString(2));
            }
        }
        closeDB();
        return res;
    }

    //devuelve cantidad, peso, descripocion, origen, categoria, id_inventario de un objeto
    public List<String> getAtrObjeto(String nombre) {
        List<String> res = new ArrayList<>();
        openDB();
        db = getReadableDatabase();
        if (db != null) {
            String[] selectionArgs = {nombre};
            Cursor c = db.rawQuery("SELECT cantidad, peso, descripcion, origen, categoria, id_inventario " +
                    "FROM t_objeto WHERE nombre = ?", selectionArgs);
            if (c != null) {
                c.moveToFirst();
                res.add(c.getString(0));
                res.add(c.getString(1));
                res.add(c.getString(2));
                res.add(c.getString(3));
                res.add(c.getString(4));
                res.add(c.getString(5));
            }
        }
        closeDB();
        return res;
    }

    public int getPeso(String asentamiento) {
        int peso = -1;
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento};
            Cursor c = db.rawQuery("SELECT t_inventario.peso_acumulado FROM t_inventario INNER JOIN t_asentamiento ON t_inventario.id = t_asentamiento.id_inventario WHERE t_asentamiento.nombre = ?", selectionArgs);
            if(c != null){
                c.moveToFirst();
                peso = c.getInt(0);
            }
        }
        closeDB();
        return peso;
    }

    public int getCapacidad(String asentamiento) {
        int capacidad = -1;
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento};
            Cursor c = db.rawQuery("SELECT t_inventario.capacidad FROM t_inventario INNER JOIN t_asentamiento ON t_inventario.id = t_asentamiento.id_inventario WHERE t_asentamiento.nombre = ?", selectionArgs);
            if(c != null){
                c.moveToFirst();
                capacidad = c.getInt(0);
            }
        }
        closeDB();
        return capacidad;
    }

    public List<String> getNombreObjetosCategoria(String asentamiento, String categoria) {
        List<String> list = new ArrayList<>();
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento, categoria};
            Cursor c = db.rawQuery("SELECT t_objeto.nombre FROM t_objeto INNER JOIN t_inventario ON t_objeto.id_inventario = t_inventario.id INNER JOIN t_asentamiento ON t_inventario.id = t_asentamiento.id_inventario WHERE t_asentamiento.nombre = ? AND t_objeto.categoria = ?", selectionArgs);
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

    public boolean InventariosIsNotEmpty(String asentamiento) {
        boolean exist = false;
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento};
            Cursor c = db.rawQuery("SELECT t_objeto.nombre FROM t_objeto INNER JOIN t_inventario ON t_objeto.id_inventario = t_inventario.id INNER JOIN t_asentamiento ON t_inventario.id = t_asentamiento.id_inventario WHERE t_asentamiento.nombre = ?", selectionArgs);
            if(c != null){
                exist = c.moveToFirst();
            }
        }
        closeDB();
        return exist;
    }
}
