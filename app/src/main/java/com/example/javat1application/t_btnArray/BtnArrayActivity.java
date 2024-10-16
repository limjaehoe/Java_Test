package com.example.javat1application.t_btnArray;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class BtnArrayActivity extends AppCompatActivity {
    private Button[] btn = new Button[10];
    private Integer[] btn_id = {R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,
            R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_btn_array);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for(int i=0; i<btn.length; i++){
            btn[i] = findViewById(btn_id[i]);
            btn[i].setVisibility(Button.VISIBLE);// 보이게
            //btn[i].setVisibility(Button.VISIBLE);// 안보이게
            //btn[i].setEnabled(true);
            //btn[i].setBackgroundResource(R.color.black);

        }


    }
}