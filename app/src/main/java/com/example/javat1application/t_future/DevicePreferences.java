package com.example.javat1application.t_future;

import android.content.Context;
import android.content.SharedPreferences;

public class DevicePreferences {

    private static final String PREF_KV_VALUE = "kv_value";
    private static final String PREF_MA_VALUE = "ma_value";
    private final SharedPreferences preferences;

    public DevicePreferences(Context context) {
        preferences = context.getSharedPreferences("device_settings", Context.MODE_PRIVATE);
    }

    public void saveKvValue(int value) {
        preferences.edit().putInt(PREF_KV_VALUE, value).apply();
    }

    public int getKvValue() {
        return preferences.getInt(PREF_KV_VALUE, 0);
    }
}
