package com.example.javat1application.t_threadhandler2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;
//https://junyoung-developer.tistory.com/118
public class ThreadHandlerTest3Activity extends AppCompatActivity {
    EditText editText;
    static TextView textView3;
    TextView textView4;

    ProcessThread thread;
    Handler mainHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thread_handler_test3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.looper_edittext);
        textView3 = findViewById(R.id.looper_textview);

        Button button = findViewById(R.id.looper_button);
        button.setOnClickListener(v -> {
            String input = editText.getText().toString();
            Message message = Message.obtain();
            message.obj = input;
            thread.processHandler.sendMessage(message);
        });

        thread = new ProcessThread(mainHandler);
    }





}