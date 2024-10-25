package com.example.javat1application.t_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class HandlerTestActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_handler_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textview_handler);

        // 백그라운드 스레드에서 작업 수행
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 예를 들어, 긴 작업을 시뮬레이션
                try {
                    Thread.sleep(5000); // 2초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 작업 결과를 UI 스레드로 전달
                final String result = "작업 완료!";

                // UI 업데이트는 UI 스레드에서 수행해야 하므로 Handler 사용
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(result);
                    }
                });
            }
        }).start(); // 스레드 시작



    }
}