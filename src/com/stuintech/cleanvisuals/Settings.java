package com.stuintech.cleanvisuals;

import java.util.*;
import android.content.*;
import android.util.SparseArray;

public class Settings
{
	private static Map<String, Boolean> values = new HashMap<>();
	private static SparseArray<String> ids = new SparseArray<>();
	private static SharedPreferences preferences;

	//Retrieve variable array from source
	public static void load(SharedPreferences preferences, String[] keys) {
		load(preferences, keys, false);
	}

	public static void load(SharedPreferences preferences, String[] keys, boolean base) {
		Settings.preferences = preferences;
		
		for(String key : keys) {
			values.put(key, preferences.getBoolean(key, base));
		}
	}

	//Save new value to source and array
	public static void set(String key, boolean value) {
		if(values.containsKey(key)) {
			preferences.edit().putBoolean(key, value).apply();
			values.remove(key);
			values.put(key, value);
		}
	}

	//Retrieve variable from array
	public static boolean get(String key) {
	    if(values.containsKey(key))
		    return values.get(key);
	    return false;
	}

	//Link id to specific variable
	public static boolean linkId(int id, String key) {
		if(values.containsKey(key))
			ids.put(id, key);
		return get(key);
	}

	//Save variable by id
	public static void setId(int id, boolean value) {
		set(ids.get(id), value);
	}

	//Convert list of variables to int
	public static int exporter(String[] keys) {
        int data = 0;
        for(String key : keys) {
            data = (data << 1) | (get(key) ? 1 : 0);
        }

        return data;
    }

    //Convert int to list of variables
    public static void importer(String[] keys, int data) {
	    int i = 0;
	    for(String key : keys) {
            set(key, (data & (1 << i)) != 0);
            i++;
        }
    }
}
