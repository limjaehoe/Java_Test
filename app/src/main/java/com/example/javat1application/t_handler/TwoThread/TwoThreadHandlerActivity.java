package com.example.javat1application.t_handler.TwoThread;

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

public class TwoThreadHandlerActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final Object lock = new Object(); // 스레드 간 동기화를 위한 객체
    private String sharedData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_two_thread_handler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textView = findViewById(R.id.textview_twothread_handler);

        // 데이터 생성 스레드
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 데이터 생성 (예: 1초 대기 후 데이터 설정)
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    sharedData = "스레드에서 생성된 데이터!";
                    lock.notify(); // 데이터가 준비되었음을 알림
                }
            }
        }).start();

        // 데이터 수신 스레드
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait(); // 데이터가 준비될 때까지 대기
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 데이터 수신 후 UI 업데이트
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(sharedData);
                        }
                    });
                }
            }
        }).start();


    }
}