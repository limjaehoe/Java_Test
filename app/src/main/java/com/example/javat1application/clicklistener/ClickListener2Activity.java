package com.example.javat1application.clicklistener;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;
//https://boheeee.tistory.com/23
public class ClickListener2Activity extends AppCompatActivity {
    Button first_btn , second_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cccc_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //https://anovice-dp.tistory.com/304
        //플러그인8부터 상수 표현식 더 이상 선언되지 않기에 발생한 오류다.

        /*first_btn = findViewById(R.id.first_btn);
        second_btn = findViewById(R.id.second_btn);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //첫번째 버튼 행동
                    case R.id.first_btn:
                        break;
                    //두번째 버튼 행동
                    case R.id.second_btn:
                        break;
                }
            }
        };

        first_btn.setOnClickListener(onClickListener);
        second_btn.setOnClickListener(onClickListener);*/

    }
}