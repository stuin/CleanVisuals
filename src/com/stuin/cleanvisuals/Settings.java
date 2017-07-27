package com.stuin.cleanvisuals;

import java.util.*;
import android.content.*;
import android.preference.*;
import android.util.SparseArray;

public class Settings
{
	private static Map<String, Boolean> values = new HashMap<>();
	private static Map<Integer, String> ids = new HashMap<>();
	private static SharedPreferences preferences;

	public static void load(SharedPreferences preferences, String[] keys) {
		load(preferences, keys, false);
	}

	public static void load(SharedPreferences preferences, String[] keys, boolean base) {
		Settings.preferences = preferences;
		
		for(String key : keys) {
			values.put(key, preferences.getBoolean(key, base));
		}
	}
	
	public static void set(String key, boolean value) {
		if(values.containsKey(key)) {
			preferences.edit().putBoolean(key, value).apply();
			values.remove(key);
			values.put(key, value);
		}
	}
	
	public static boolean get(String key) {
		return values.get(key);
	}
	
	public static boolean linkId(int id, String key) {
		ids.put(id, key);
		return get(key);
	}
	
	public static void setId(int id, boolean value) {
		if(ids.containsKey(id)) {
			set(ids.get(id), value);
		}
	}
}
