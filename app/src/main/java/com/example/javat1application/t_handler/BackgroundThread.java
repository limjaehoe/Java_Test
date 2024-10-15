package com.example.javat1application.t_handler;

import android.os.Bundle;
import android.os.Message;

public class BackgroundThread extends Thread {
    ValueHandler handler = new ValueHandler();
    int value = 0;
    boolean running = false;
    public void run() {
        running = true;
        while(running) {
            value += 1;
            System.out.println("value: " + value);

            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putInt("value", value);
            message.setData(bundle);
            handler.sendMessage(message);


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
