package com.example.javat1application.t_threadhandler2;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
public class ProcessHandler extends Handler {

    private Handler mainHandler;

    public ProcessHandler(Handler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        final String output  = msg.obj + "from thread";

        mainHandler.post(() -> {
            // You may need to pass textView as an argument or use another method to update UI

            ThreadHandlerTest3Activity.textView3.setText(output);
            //((ThreadHandlerTest3Activity) mainHandler).textView.setText(output);
            //textView.setText(output); // textView is not defined
        });

//        final String output = msg.obj + "from thread";
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText(output);
//            }
//        });

    }
}

