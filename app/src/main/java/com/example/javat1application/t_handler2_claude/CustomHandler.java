package com.example.javat1application.t_handler2_claude;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class CustomHandler extends Handler {
    private static final String TAG = "CustomHandler";
    private WeakReference<MainHandlerActivity> activityReference;

    public CustomHandler(MainHandlerActivity activity) {
        // 메모리 누수 방지를 위해 WeakReference 사용
        this.activityReference = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        MainHandlerActivity activity = activityReference.get();
        if (activity == null) return;

        switch (msg.what) {
            case MessageTypes.UPDATE_PROGRESS:
                // 진행률 업데이트
                int progress = msg.arg1;
                activity.updateProgressBar(progress);

                break;

            case MessageTypes.TASK_COMPLETE:
                // 작업 완료
                Bundle data = msg.getData();
                String result = data.getString("result");
                activity.showTaskComplete(result);
                break;

            case MessageTypes.TASK_ERROR:
                // 에러 발생
                activity.showError("작업 중 오류가 발생했습니다.");
                break;
        }
    }
}
