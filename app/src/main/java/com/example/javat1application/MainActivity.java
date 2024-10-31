package com.example.javat1application;





import static com.example.javat1application.StaticTest.helloWorld;
import static com.example.javat1application.t_car.Cat.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.t_car.Car;
import com.example.javat1application.t_car.EvCar;
import com.example.javat1application.t_car.Cat;
import com.example.javat1application.t_handler.BackgroundThread;
import com.example.javat1application.t_handler2_claude.MainHandlerActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button nextbutton = findViewById(R.id.next_button);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainHandlerActivity.class);
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.textView);


        String[] masTable = {"0.1", "0.12", "0.16", "0.2", "0.25", "0.32", "0.4", "0.5", "0.63",
                "0.8", "1", "1.25", "1.6", "2.0", "2.5", "3.2", "4.0", "5.0", "6.3", "8.0","10.0",
                "12.5", "16.0", "20.0", "25.0", "32.0", "40.0", "50.0", "63.0", "80.0", "100.0", "125.0", "160.0",
                "200.0", "250.0", "320.0", "400.0", "500.0", "630.0"};

        int sum;

        //상속
        Car car = new Car();

        car.drive();
        sum = car.carcluate(1, 2);
        System.out.println("sum: " + sum);

        EvCar evCar = new EvCar();
        evCar.drive();
        sum = evCar.carcluate(1, 2);
        System.out.println("sum: " + sum);

        System.out.println("sum: " + masTable[3]);
        for(int i = 0; i < masTable.length; i++) {
            System.out.println("sum: " + masTable[i]);
        }


        //리스트
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bcd");

        ArrayList a = new ArrayList();
        a = list;
        a.add("4");


        for(int i = 0; i < list.size(); i++) {
            System.out.println("sum: " + list.get(i));
        }


        for(int i = 0; i < a.size(); i++) {
            System.out.println("sum: " + a.get(i));
        }
        //리스트


        //클래스
        Cat myCat = new Cat("Tom", 3, "Black");
        myCat.printInfo();

        System.out.println("aaa"+myCat.getName());
        System.out.println(hotel);
        System.out.println(helloWorld);


        //핸들러 스레드
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });


    }
}

