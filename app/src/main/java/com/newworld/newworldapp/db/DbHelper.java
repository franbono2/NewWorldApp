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
    private static final String INSERT_OBJETO = "INSERT INTO t_objeto (nombre,cantidad,peso,descripcion,origen,categoria,id_inventario)" +
            "VALUES ('Abretormentas',1,10,'«El sol asomó entre las nubes con la promesa de un futuro mejor»','Enemigos','Armas', 0)," +
            " ('Abrigo de aventurero',1,6,'Un abrigo bien pertrechado, te servira igual de bien que a su antigo dueño','Enemigos','Armaduras', 0)," +
            " ('Abismo inmortal',1,3,'«Quienes caen dentro jamás escapan»','Enemigos','Armas', 1)," +
            " ('Ablandador',1,5,'«Igual de efectivo con los enemigos que con la carne»','Enemigos','Armas', 2)," +
            " ('Agonia',1,2,'«Al principio te dolerá. Y después te dolerá todavía más. Pero no hay nada que puedas hacer»','Enemigos','Armas', 3)," +
            " ('Pecado',1,4,'«Purgaremos a los corruptos con nuestra furia justificada»','Enemigos','Armas', 4)," +
            " ('Lanza omega',1,2,'Versión omega de la lanza','Enemigos','Armas', 5)," +
            " ('Ocaso',1,1,'«Cuando la oscuridad alcanza su cénit, el vacío sonríe dichoso»','Enemigos','Armas', 6)," +
            " ('Cierragrietas',1,12,'«¡Defiende nuestro asentamiento! ¡Cierra esa grieta!»','Enemigos','Armas', 7)," +
            " ('Pacifista',1,3,'«No te deseo ningún mal. Apártate y nadie saldrá herido»','Enemigos','Armas', 8)," +
            " ('Latido',1,4,'«Lo primero y último que oirás en la vida»','Enemigos','Armas', 9)," +
            " ('Galanteria',1,4,'«Una espada para el valiente, el refinado, el más galante de nuestros caballeros»','Enemigos','Armas', 10)," +
            " ('Sombrero totemico',1,2,'«Se piensa que los cuernos tenían un fin especial, pero nadie se acuerda de cuál era»','Enemigos','Armaduras', 0)," +
            " ('Coraza sacrosanta',1,20,'Armadura concedida por vencer a uno de los hechizos más poderosos de los antiguos','Enemigos','Armaduras', 1)," +
            " ('Grebas primitivas',1,12,'Una pieza de armadura que lleva algún tiempo en manos de los tierramarga','Enemigos','Armaduras', 1)," +
            " ('Pendientes omega',1,1,'Versión omega de los pendientes','Enemigos','Armaduras', 7)," +
            " ('Yelmo de inquisidor',1,3,'Armadura que llevan los que han jurado sacar a la luz las conspiraciones de disidentes y herejes','Enemigos','Armaduras', 4)," +

            " ('Pocion de salud',30,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',0)," +
            " ('Pocion de salud',20,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',1)," +
            " ('Pocion de salud',10,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',2)," +
            " ('Pocion de salud',40,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',3)," +
            " ('Pocion de salud',17,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',4)," +
            " ('Pocion de salud',26,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',5)," +
            " ('Pocion de salud',54,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',6)," +
            " ('Pocion de salud',32,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',7)," +
            " ('Pocion de salud',33,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',8)," +
            " ('Pocion de salud',28,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',9)," +
            " ('Pocion de salud',14,2,'Consumible para aumentar la salud','Arcana, enemigos','consumibles',10)," +

            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',0)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',1)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',3)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',4)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',5)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',6)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',7)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',9)," +
            " ('Pocion de mana',20,2,'Consumible para aumentar el mana','Arcana, enemigos','Consumibles',10)," +

            " ('Racion ligera',20,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',0)," +
            " ('Racion ligera',40,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',1)," +
            " ('Racion ligera',30,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',2)," +
            " ('Racion ligera',20,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',3)," +
            " ('Racion ligera',10,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',4)," +
            " ('Racion ligera',45,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',5)," +
            " ('Racion ligera',32,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',6)," +
            " ('Racion ligera',27,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',7)," +
            " ('Racion ligera',15,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',8)," +
            " ('Racion ligera',23,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',9)," +
            " ('Racion ligera',29,2,'Consumible para activar la regeneracion de salud','Cocina, enemigos','Consumibles',10)," +

            " ('Racion de viaje',10,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',0)," +
            " ('Racion de viaje',27,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',1)," +
            " ('Racion de viaje',56,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',2)," +
            " ('Racion de viaje',31,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',3)," +
            " ('Racion de viaje',19,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',5)," +
            " ('Racion de viaje',22,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',7)," +
            " ('Racion de viaje',28,3,'Consumible para aumentar considerablemente la regeneracion de salud','Cocina, enemigos','Consumibles',10)," +

            " ('Agua',1,47,'Ingrediente de cocina. Se puede consumir, pero solo restaura una cantidad muy pequeña de salud. Los ingredientes son mucho más eficaces cuando se cocinan.','Masas de agua','Consumibles',0)," +
            " ('Agua',1,47,'Ingrediente de cocina. Se puede consumir, pero solo restaura una cantidad muy pequeña de salud. Los ingredientes son mucho más eficaces cuando se cocinan.','Masas de agua','Materiales',0)," +
            " ('Agua',1,30,'Ingrediente de cocina. Se puede consumir, pero solo restaura una cantidad muy pequeña de salud. Los ingredientes son mucho más eficaces cuando se cocinan.','Masas de agua','Consumibles',8)," +
            " ('Agua',1,30,'Ingrediente de cocina. Se puede consumir, pero solo restaura una cantidad muy pequeña de salud. Los ingredientes son mucho más eficaces cuando se cocinan.','Masas de agua','Materiales',8)," +
            " ('Agua',1,21,'Ingrediente de cocina. Se puede consumir, pero solo restaura una cantidad muy pequeña de salud. Los ingredientes son mucho más eficaces cuando se cocinan.','Masas de agua','Consumibles',4)," +
            " ('Agua',1,21,'Ingrediente de cocina. Se puede consumir, pero solo restaura una cantidad muy pequeña de salud. Los ingredientes son mucho más eficaces cuando se cocinan.','Masas de agua','Materiales',4)," +

            " ('Paella',5,6,'Aumenta la destreza y la concentración en 12 durante 30 minutos','Cocina, enemigos','Consumibles',0)," +
            " ('Paella',7,6,'Aumenta la destreza y la concentración en 12 durante 30 minutos','Cocina, enemigos','Consumibles',3)," +
            " ('Paella',2,6,'Aumenta la destreza y la concentración en 12 durante 30 minutos','Cocina, enemigos','Consumibles',6)," +
            " ('Paella',4,6,'Aumenta la destreza y la concentración en 12 durante 30 minutos','Cocina, enemigos','Consumibles',9)" +

            ";";

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

    public boolean ObjectsIsNotEmpty(String asentamiento, String categoria) {
        boolean exist = false;
        openDB();
        db = getReadableDatabase();
        if(db != null){
            String[] selectionArgs = {asentamiento, categoria};
            Cursor c = db.rawQuery("SELECT t_objeto.nombre FROM t_objeto INNER JOIN t_inventario ON t_objeto.id_inventario = t_inventario.id INNER JOIN t_asentamiento ON t_inventario.id = t_asentamiento.id_inventario WHERE t_asentamiento.nombre = ? AND t_objeto.categoria = ?", selectionArgs);
            if(c != null){
                exist = c.moveToFirst();
            }
        }
        closeDB();
        return exist;
    }
}
