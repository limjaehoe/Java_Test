package com.example.javat1application.t_interface2_handler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.javat1application.MainActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class T5HAutoHandler extends Handler implements HandlerInterface {

    private WeakReference<Context> contextReference;

    public T5HAutoHandler(Context context) {
        this.contextReference = new WeakReference<>(context);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        Context context = contextReference.get();
        if (context != null && msg.what == 1) {
            String updatedCount = (String) msg.obj;

            // UI 업데이트: MainActivity의 TextView를 업데이트
            Toast.makeText(context, updatedCount, Toast.LENGTH_SHORT).show();

            // 여기에 UI 업데이트 코드를 추가할 수 있습니다.
            // 예를 들어, MainActivity의 TextView를 업데이트 하는 로직을 구현할 수 있습니다.
        }
    }
}
