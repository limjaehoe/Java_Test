package com.example.javat1application.t_ms_calculrate;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsCalculrateMainActivity extends AppCompatActivity {

    private static final String TAG = "MsCalculrateMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ms_calculrate_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 현재 시간을 밀리초로 가져오기
        long startTimeMillis = System.currentTimeMillis();
        System.out.println("Start time in milliseconds: " + startTimeMillis);

        // 10초 동안 스레드 대기
        try {
            System.out.println("Sleeping for 10 seconds...");
            Thread.sleep(3000); // 10000 밀리초 = 10초
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 다시 현재 시간을 밀리초로 가져오기
        long endTimeMillis = System.currentTimeMillis();
        System.out.println("End time in milliseconds: " + endTimeMillis);

        // 경과 시간 계산
        long elapsedTimeMillis = endTimeMillis - startTimeMillis;
        System.out.println("Elapsed time in milliseconds: " + elapsedTimeMillis);

    }
}