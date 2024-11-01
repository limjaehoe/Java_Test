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
import java.util.concurrent.Future;

public class ExecutorServiceActivity extends AppCompatActivity {
    private static final String TAG = "ManufacturingExample";
    private ExecutorService executorService;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_executor_service);


        resultTextView = findViewById(R.id.resultTextView);
        executorService = Executors.newFixedThreadPool(3); // 3개의 스레드를 가진 풀

        // 비동기 작업 실행
        executeManufacturingProcess();

    }

    // 제조 과정을 실행하는 메서드
    // 재료 준비 -> 제조 -> 포장
    private void executeManufacturingProcess() {
        // 재료 준비 작업
        Future<String> ingredientsFuture = executorService.submit(() -> {
            Log.d(TAG, "재료 준비 시작1");
            Thread.sleep(3000); // 2초 대기 (재료 준비 시뮬레이션)
            Log.d(TAG, "재료 준비 완료2");
            return "재료 준비 완료2";
        });

        // 제조 작업
        Future<String> manufacturingFuture = executorService.submit(() -> {
            //String ingredientsResult = ingredientsFuture.get(); // 재료 준비 결과를 기다림
            //Log.d(TAG, "제조 시작3: " + ingredientsResult);
            Log.d(TAG, "제조 시작3: " );
            Thread.sleep(3000); // 3초 대기 (제조 시뮬레이션)
            Log.d(TAG, "제조 완료4");
            return "물건 제조 완료4";
        });

        // 포장 작업
        executorService.execute(() -> {
            try {
                String manufacturingResult = manufacturingFuture.get(); // 제조 결과를 기다림
                Log.d(TAG, "포장 시작5: " + manufacturingResult);
                Thread.sleep(5000); // 1초 대기 (포장 시뮬레이션)
                Log.d(TAG, "포장 완료6");

                // 최종 결과 UI 업데이트
                runOnUiThread(() -> resultTextView.setText("모든 과정 완료: " + manufacturingResult));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // ExecutorService 종료
    }
}