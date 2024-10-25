package com.example.javat1application.t_interface2_handler;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class InterHandlerActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private HandlerInterface t5hHandler;
    private HandlerInterface  t5hAnotherHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inter_handler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.interhand_text);
        button = findViewById(R.id.interhand_button);

        t5hHandler = new T5HAutoHandler(this);
        t5hAnotherHandler = new T5HAnotherHandler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T5H.getInstance().incrementCount();
                textView.setText("Count: " + T5H.getInstance().getCount());

                // 1초 후에 UI 업데이트
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000); // 1초 대기
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 첫 번째 핸들러에 메시지 전송
                        //Message message = t5hHandler.obtainMessage(1, "Count after 1 second: " + T5H.getInstance().getCount());
                        //t5hHandler.sendMessage(message); // 메시지 큐에 추가

                        //Message message1 = t5hHandler.obtainMessage(1, "Count after 1 second: " + T5H.getInstance().getCount());
                        //t5hHandler.handleMessage(message1); // 인터페이스를 통해 처리

                        // 두 번째 핸들러에 메시지 전송
                        Message message2 = ((T5HAnotherHandler) t5hAnotherHandler).obtainMessage(2, "Another message after 1 second");
                        t5hAnotherHandler.handleMessage(message2); // 인터페이스를 통해 처리
                    }
                }).start();
            }
        });
    }

    // TextView를 업데이트하는 메서드
    public void updateTextView(String updatedCount) {
        textView.setText(updatedCount);
    }
}