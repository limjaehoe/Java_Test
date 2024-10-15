package com.example.javat1application.t_enum;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class EnumActivity extends AppCompatActivity {

    //https://limkydev.tistory.com/66
    //https://limkydev.tistory.com/50

    public String name;
    public int career;
    public DevType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enum);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Developer developer = new Developer();
        developer.name = "John";
        developer.career = 3;
        developer.type = DevType.MOBILE;

        Abc a = Abc.A;
        //enum은 static이랑 비슷한데 = final static string 이랑 비슷하다.
        //차이점은

        System.out.println(developer.name + " " + developer.career + " " + developer.type + a);



    }
}