package com.example.javat1application.t_future;

import android.util.Log;

public class SerialCommunicator {
    private boolean isConnected = false;

    public void connect() throws Exception {
        // 실제 시리얼 포트 연결 로직
        Thread.sleep(1000); // 시뮬레이션
        isConnected = true;
    }

    public void sendCommand(String command) throws Exception {
        if (!isConnected) {
            throw new IllegalStateException("Not connected");
        }
        // 실제 명령어 전송 로직
        Log.d("SerialCommunicator", "Sending command: " + command);
        Thread.sleep(100); // 시뮬레이션
    }
}