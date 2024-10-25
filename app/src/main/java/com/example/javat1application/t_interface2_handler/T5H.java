package com.example.javat1application.t_interface2_handler;

import android.content.Context;

public class T5H {

    private static T5H instance;
    private int count = 0;

    private T5H() {
        // private constructor to prevent instantiation
    }

    public static T5H getInstance() {
        if (instance == null) {
            instance = new T5H();
        }
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }
}
