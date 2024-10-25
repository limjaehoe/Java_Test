package com.example.javat1application.clicklistener;

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

public class ImplementsListenerActivity extends AppCompatActivity {

    //https://boheeee.tistory.com/23

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implements_listener);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BtnOnClick btnOnClick = new BtnOnClick();
        Button btn1 = (Button) findViewById(R.id.imp_btn1);
        btn1.setOnClickListener(btnOnClick);
        Button btn2 = (Button) findViewById(R.id.imp_btn2);
        btn2.setOnClickListener(btnOnClick);

    }

    // 클래스를 따로 생성하여 이벤트 리스너 상속
    class BtnOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imp_btn1:
                    Toast.makeText(ImplementsListenerActivity.this, "버튼1", Toast.LENGTH_SHORT).show();
                    System.out.println("버튼1 클릭");
                    // btn1 동작
                    break;
                case R.id.imp_btn2:
                    Toast.makeText(ImplementsListenerActivity.this, "버튼2", Toast.LENGTH_SHORT).show();
                    System.out.println("버튼2 클릭");
                    // btn2 동작
                    break;
            }
        }
    }

}