package com.carlostorres.ibericajava.data.local.prefrns;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefernsProvidr {

    public PrefernsProvidr() {
    }

    public static boolean saveUser(String user, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", user);
        editor.apply();
        return true;
    }

    public static String getUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("usuario", null);
    }

}
