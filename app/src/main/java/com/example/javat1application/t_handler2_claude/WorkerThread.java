package com.example.javat1application.t_handler2_claude;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WorkerThread extends Thread {
    private static final String TAG = "WorkerThread";
    private Handler handler;
    private boolean isRunning = true;

    public WorkerThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            // 작업 진행 시뮬레이션
            for (int i = 0; i <= 100 && isRunning; i += 1) {
                // 진행률 업데이트 메시지 전송
                Message progressMsg = Message.obtain();
                progressMsg.what = MessageTypes.UPDATE_PROGRESS;
                progressMsg.arg1 = i;
                handler.sendMessage(progressMsg);
                Log.d(TAG, "Progress: " + i);

                // 작업 시뮬레이션
                Thread.sleep(100);
            }

            // 작업 완료 메시지 전송
            Message completeMsg = Message.obtain();
            completeMsg.what = MessageTypes.TASK_COMPLETE;
            Bundle data = new Bundle();
            data.putString("result", "작업이 성공적으로 완료되었습니다.");
            completeMsg.setData(data);
            handler.sendMessage(completeMsg);

        } catch (InterruptedException e) {
            // 에러 메시지 전송
            Message errorMsg = Message.obtain();
            errorMsg.what = MessageTypes.TASK_ERROR;
            handler.sendMessage(errorMsg);

            Log.e(TAG, "Worker thread interrupted", e);
        } finally {
            // 메시지 객체 정리
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void stopWork() {
        isRunning = false;
        interrupt();
    }
}