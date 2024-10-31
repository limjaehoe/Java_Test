package com.example.javat1application.t_handler2_claude;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javat1application.R;

public class MainHandlerActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CustomHandler handler;
    private WorkerThread workerThread;
    private ProgressBar progressBar;
    private TextView statusText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_handler);

        progressBar = findViewById(R.id.progressBar);
        statusText = findViewById(R.id.statusText);


        // Handler 초기화
        handler = new CustomHandler(this);

        // 시작 버튼 설정
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> startWorkerThread());

        // 중지 버튼 설정
        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(v -> stopWorkerThread());
    }

    private void startWorkerThread() {
        if (workerThread != null && workerThread.isAlive()) {
            Toast.makeText(this, "이미 작업이 진행 중입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        workerThread = new WorkerThread(handler);
        workerThread.start();
    }

    private void stopWorkerThread() {
        if (workerThread != null) {
            workerThread.stopWork();
            workerThread = null;
        }
    }

    // UI 업데이트 메소드들
    public void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
        statusText.setText("진행률: " + progress + "%");
    }

    public void showTaskComplete(String result) {
        statusText.setText(result);
        Toast.makeText(this, "작업 완료!", Toast.LENGTH_SHORT).show();
    }

    public void showError(String error) {
        statusText.setText(error);
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopWorkerThread();
        handler.removeCallbacksAndMessages(null);
    }
}