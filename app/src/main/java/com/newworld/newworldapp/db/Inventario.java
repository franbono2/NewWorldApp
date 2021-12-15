package com.newworld.newworldapp.db;

public class Inventario {
    private int id;
    private int capacidad;
    private int peso_acumulado;

    public Inventario(int id, int capacidad, int peso_acumulado) {
        this.id = id;
        this.capacidad = capacidad;
        this.peso_acumulado = peso_acumulado;
    }
}
