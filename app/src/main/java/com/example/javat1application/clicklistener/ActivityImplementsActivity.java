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

public class ActivityImplementsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implements);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        Button btn1 = (Button) findViewById(R.id.activity_imp_btn1);
//        btn1.setOnClickListener(btnOnClick);
//        Button btn2 = (Button) findViewById(R.id.activity_imp_btn2);
//        btn2.setOnClickListener(btnOnClick);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_imp_btn1:
                Toast.makeText(ActivityImplementsActivity.this, "버튼1", Toast.LENGTH_SHORT).show();
                System.out.println("버튼1 클릭");
                // do something
                break;
            case R.id.activity_imp_btn2:
                Toast.makeText(ActivityImplementsActivity.this, "버튼2", Toast.LENGTH_SHORT).show();
                System.out.println("버튼2 클릭");
                // do something
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}