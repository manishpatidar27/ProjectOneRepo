package com.androidgaming.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class CustomPreference {

    private static Context mContext;
    public static final String PREFS_FILE_NAME = "and_gam_pref";
    private static CustomPreference customPreference = null;
    private SharedPreferences settings;
    private Context context;



    public CustomPreference() {

        this.settings = mContext.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static CustomPreference getInstance(Context context) {
        if (customPreference == null) {
            mContext = context;
            customPreference = new CustomPreference();
        }
        return customPreference;
    }

    public void addValue(PreferenceKeys preferencesKey, String text) {
        Editor editor;
        editor = settings.edit();
        editor.putString(preferencesKey.getKey(), text);
        editor.commit();
    }
    public void addValue(String preferencesKey, String text) {
        Editor editor;
        editor = settings.edit();
        editor.putString(preferencesKey, text);
        editor.commit();
    }

    public String getValue(PreferenceKeys preferencesKey) {
        String text;
        text = settings.getString(preferencesKey.getKey(), "");
        return text;
    }


    public void addStringList(PreferenceKeys preferencesKey, ArrayList<String> storedList) {
        Set<String> set = new HashSet<String>();
        set.addAll(storedList);
        Editor editor;
        editor = settings.edit();
        editor.putStringSet(preferencesKey.getKey(), set);
        editor.commit();
    }


    public Set<String> getStringList(PreferenceKeys preferencesKey) {

        Set<String> set = null;
        try {
            set = settings.getStringSet(preferencesKey.getKey(), new HashSet<String>());
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }


    public void clearSharedPreference() {
        Editor editor;
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }


    public void removeValue(PreferenceKeys preferencesKey) {
        Editor editor;
        editor = settings.edit();
        editor.putString(preferencesKey.getKey(), "");
        editor.commit();


    }

    public void removeValueOfList(PreferenceKeys preferencesKey) {
        Set<String> set = new HashSet<String>();
        Editor editor;
        editor = settings.edit();
        editor.putStringSet(preferencesKey.getKey(), set);
        editor.commit();


    }
}
