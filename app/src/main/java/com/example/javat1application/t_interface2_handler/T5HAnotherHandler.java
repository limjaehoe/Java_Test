package com.example.javat1application.t_interface2_handler;

import android.os.Handler;
import android.widget.Toast;

public class T5HAnotherHandler extends Handler implements HandlerInterface {
    @Override
    public void handleMessage(android.os.Message msg) {
        if (msg.what == 2) {
            String message = (String) msg.obj;


            // 다른 UI 업데이트 로직 (예: Toast 메시지 표시 등)
            // 예: Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
