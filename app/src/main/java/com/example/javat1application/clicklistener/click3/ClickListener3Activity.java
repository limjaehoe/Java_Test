package com.example.javat1application.clicklistener.click3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;
import com.example.javat1application.clicklistener.ClickListenerActivity;

public class ClickListener3Activity extends AppCompatActivity {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_click_listener3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button1 = findViewById(R.id.click3_btn1);
        button2 = findViewById(R.id.click3_btn2);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // do something when the button is clicked


            switch (v.getId()) {
                case R.id.click_btn1:
                    System.out.println("버튼1 클릭");
                    Toast.makeText(getApplicationContext(), "버튼1 클릭", Toast.LENGTH_SHORT).show();
                    // do something
                    break;
                case R.id.click_btn2:
                    // do something
                    System.out.println("버튼2 클릭");
                    Toast.makeText(getApplicationContext(), "버튼2 클릭", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), ClickListenerActivity.class);
                    startActivity(intent2);
                    break;
                default:
                    break;

            }
        }
    };

}