package com.example.javat1application.t_handler;

import android.os.Bundle;
import android.os.Message;

public class ValueHandler extends android.os.Handler {

    @Override

    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        Bundle bundle = msg.getData();
        int value = bundle.getInt("value");
        System.out.println("value: " + value);


    }
}