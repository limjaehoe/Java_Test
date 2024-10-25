package com.example.javat1application.t_threadhandler2;

import android.os.Handler;
import android.os.Looper;

class ProcessThread extends Thread {
    ProcessHandler processHandler;

    public ProcessThread(Handler mainHandler) {
        processHandler = new ProcessHandler(mainHandler);
    }
    @Override
    public void run() {
        super.run();
        Looper.prepare();
        Looper.loop();
    }
}