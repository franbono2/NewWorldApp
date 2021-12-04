package com.newworld.newworldapp;

import java.util.HashMap;

public final class SingletonMap extends HashMap<String, Object>{
    private static class SingletonHolder {
        private static final SingletonMap ourInstance = new SingletonMap();
    }
    public static SingletonMap getInstance() {
        return SingletonHolder.ourInstance;
    }
    private SingletonMap() {}
}
