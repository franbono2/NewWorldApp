package com.newworld.newworldapp.db;

public class Articulo {
    private int id;
    private String nombre;
    private int cantidad;
    private int peso;
    private String descripcion;
    private String origen;
    private String categoria;
    private Inventario inventario;


    public Articulo(int id, String nombre, int cantidad, int peso, String descripcion, String origen, String categoria, Inventario inventario) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.peso = peso;
        this.descripcion = descripcion;
        this.origen = origen;
        this.categoria = categoria;
        this.inventario = inventario;
    }
}
