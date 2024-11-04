package com.example.javat1application.t_future;

import android.os.CountDownTimer;

public class AutoTest {
    private static final int TEST_DURATION = 60; // 60초
    private static final int INTERVAL = 1000; // 1초

    public void startTest(DeviceViewModel viewModel) {
        new CountDownTimer(TEST_DURATION * 1000, INTERVAL) {
            public void onTick(long millisUntilFinished) {
                int testValue = (int) (millisUntilFinished / 1000);
                viewModel.setKvValue(testValue);
            }

            public void onFinish() {
                // 테스트 완료
            }
        }.start();
    }
}
