package com.example.javat1application.t_thread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class ThreadActivity extends AppCompatActivity {

    //https://makecodework.tistory.com/entry/Java-%EC%8A%A4%EB%A0%88%EB%93%9CThread-%EC%A0%9C%EC%96%B4%ED%95%98%EA%B8%B0-%EC%9A%B0%EC%84%A0%EC%88%9C%EC%9C%84-%EC%84%A4%EC%A0%95-%EB%8F%99%EA%B8%B0%ED%99%94-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thread);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}