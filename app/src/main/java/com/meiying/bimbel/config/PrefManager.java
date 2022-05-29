package com.meiying.bimbel.config;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    String PREF_NAME = "bimbel_meiyin";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static PrefManager instance;
    public PrefManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, 0);
        editor = preferences.edit();
    }

    public static PrefManager getInstance(Context context) {
        return instance == null ? new PrefManager(context) : instance;
    }

    public void put(String key,String value){
        editor.putString(key, value).apply();
    }

    public void put(String key, int value){
        editor.putInt(key, value).apply();
    }

    public String getString(String key){
        return preferences.getString(key,null);
    }

    public int getInt(String key){
        return preferences.getInt(key,0);
    }

    public void clear(){
        editor.clear().apply();
    }
}