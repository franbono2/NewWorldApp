package com.newworld.newworldapp;

import java.util.HashMap;
import java.util.List;

public final class SingletonMap {
    //Diccionario -> {NombreCiudad -> {"Inventario": Articulo(clase: Arma, nombre:Conquistador avisal, tipo:Martillo) "Eventos": Evento(tipo: Guerra, fecha: 23/04/2021 18:00)}
    //private static HashMap<String, HashMap<String, List<Object>>> singletonMap;
    private static SingletonMap instance;
    public HashMap<String, HashMap<String, List<Object>>> map;

    private SingletonMap(HashMap<String, HashMap<String, List<Object>>> map){
        this.map = map;
    }

    public static SingletonMap getInstance(HashMap<String, HashMap<String, List<Object>>> map){
        if(instance == null){
            instance = new SingletonMap(map);
        }
        return instance;
    }
}
