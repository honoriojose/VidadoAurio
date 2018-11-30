package com.honoriojose.vidadoaurio;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.List;

public class VidaSharedPreferences {

    public static void saveVidaLista(List<Vida> vidas, Context context, String Key){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor= preferences.edit();
        Gson gson = new Gson();
        String json= gson.toJson(vidas);
        editor.putString(Key,json);
        editor.apply();
    }
    public static List<Vida> loadVidaList(Context context,String Key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(Key, null);
        Type type = new TypeToken<List<Vida>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
