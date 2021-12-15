package com.newworld.newworldapp.db;

import java.util.Date;

public class Evento {
    private int id;
    private String nombre;
    private String tipo;
    private Date fecha;
    private Asentamiento asentamiento;

    public Evento(int id, String nombre, String tipo, Date fecha, Asentamiento asentamiento) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.asentamiento = asentamiento;
    }
}
