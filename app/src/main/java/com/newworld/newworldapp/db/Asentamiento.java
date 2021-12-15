package com.newworld.newworldapp.db;

public class Asentamiento {
    private int id;
    private String nombre;
    private String tipo;
    private int regristrado_posada;
    private Inventario inventario;

    public Asentamiento(int id, String nombre, String tipo, int regristrado_posada, Inventario inventario) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.regristrado_posada = regristrado_posada;
        this.inventario = inventario;
    }
}
