package com.example.javat1application.t_future;

import android.content.Context;
import android.content.SharedPreferences;

public class DevicePreferences {
    private static final String PREF_NAME = "device_settings";
    private static final String PREF_KV_VALUE = "kv_value";
    private static final String PREF_MA_VALUE = "ma_value";
    private static final String PREF_IS_CONNECTED = "is_connected";

    private final SharedPreferences preferences;

    public DevicePreferences(Context context) {
        // 앱의 설정값을 저장할 SharedPreferences 초기화
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // KV 값 저장
    public void saveKvValue(int value) {
        preferences.edit()
                .putInt(PREF_KV_VALUE, value)
                .apply();
    }

    // KV 값 불러오기 (기본값 0)
    public int getKvValue() {
        return preferences.getInt(PREF_KV_VALUE, 0);
    }

    // mA 값 저장
    public void saveMaValue(int value) {
        preferences.edit()
                .putInt(PREF_MA_VALUE, value)
                .apply();
    }

    // mA 값 불러오기 (기본값 0)
    public int getMaValue() {
        return preferences.getInt(PREF_MA_VALUE, 0);
    }

    // 연결 상태 저장
    public void saveConnectionState(boolean isConnected) {
        preferences.edit()
                .putBoolean(PREF_IS_CONNECTED, isConnected)
                .apply();
    }

    // 연결 상태 불러오기
    public boolean wasConnected() {
        return preferences.getBoolean(PREF_IS_CONNECTED, false);
    }

    // 모든 설정값 저장
    public void saveAllSettings(int kv, int ma, boolean isConnected) {
        preferences.edit()
                .putInt(PREF_KV_VALUE, kv)
                .putInt(PREF_MA_VALUE, ma)
                .putBoolean(PREF_IS_CONNECTED, isConnected)
                .apply();
    }

    // 모든 설정값 초기화
    public void clearAll() {
        preferences.edit().clear().apply();
    }
}