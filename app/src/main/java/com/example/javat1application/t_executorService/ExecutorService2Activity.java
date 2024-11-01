package com.example.javat1application.t_executorService;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService2Activity extends AppCompatActivity {
    private static final String TAG = "ManufacturingExample";
    private ExecutorService executorService;
    private TextView resultTextView;
    private StringBuilder resultBuilder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_executor_service2);


        resultTextView = findViewById(R.id.resultTextView333);
        executorService = Executors.newFixedThreadPool(3); // 3개의 스레드를 가진 풀

        // 비동기 작업 실행
        executeManufacturingProcess();
    }

    // 제조 과정을 실행하는 메서드
    // 동시에 일어나서 먼저 나오는것.. 출력됩니다.
    private void executeManufacturingProcess() {
        // 재료 준비 작업
        executorService.execute(() -> {
            try {
                Log.d(TAG, "재료 준비 시작");
                Thread.sleep(2000); // 2초 대기 (재료 준비 시뮬레이션)
                Log.d(TAG, "재료 준비 완료");
                updateResult("재료 준비 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 제조 작업
        executorService.execute(() -> {
            try {
                Log.d(TAG, "제조 시작");
                Thread.sleep(3000); // 3초 대기 (제조 시뮬레이션)
                Log.d(TAG, "제조 완료");
                updateResult("물건 제조 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 포장 작업
        executorService.execute(() -> {
            try {
                Log.d(TAG, "포장 시작");
                Thread.sleep(1000); // 1초 대기 (포장 시뮬레이션)
                Log.d(TAG, "포장 완료");
                updateResult("포장 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private synchronized void updateResult(String message) {
        resultBuilder.append(message).append("\n");
        runOnUiThread(() -> resultTextView.setText(resultBuilder.toString()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // ExecutorService 종료
    }
}