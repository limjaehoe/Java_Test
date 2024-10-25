package com.example.javat1application.clicklistener;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class CompoundButtonActivity extends AppCompatActivity {
//xml에서 만들 뷰들을 참조하는 참조변수를 먼저 만들어줍니다
    //onCreate 전에 이렇게 변수로 선언를 해주셔야하는데
    // 이렇게 해놔야 어디서든 자신이 사용한 코드안에서 어디서든사용가능합니다.

    private CheckBox cb01, cb02, cb03; //cd01,cd02등 이것은 제마음대로 쓸수있습니다!
    //무조건 이걸써야하는것이아닙니다. 자유롭지만 관련있는 작명을해봅시다.
    private TextView tv;
    private ToggleButton toggleButton;
    private Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compound_button);

        // xml에서 같이 id를 만들었었죠? xml과 연결해봅시당 findviewById로
        // '이 변수는 xml에서 이것을 뜻하는것입니다~~' 하는 뜻이에용
        // id를 이용해서 뷰들을 찾아와서 참조변수에 대입!
        cb01= findViewById(R.id.cb01);
        cb02= findViewById(R.id.cb02);
        cb03= findViewById(R.id.cb03);
        tv= findViewById(R.id.tv);


        //체크박스의 체크상태가 변경되는 것을 듣는 리스너객체 생성. 코드쓰시다보면 자동완성 될겁니다!
        CompoundButton.OnCheckedChangeListener changeListener= new CompoundButton.OnCheckedChangeListener() {
            //체크상태가 변경될 때 마다 실행되는 메소드
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //저희가 썼던 과일 이름을 가져온다는 뜻입니다.
                String s=""; //문자열을 저장하는 String 변수값을 가져와 s안에 값을 저장해봅시다!
                if( cb01.isChecked()) s += cb01.getText().toString();
                if( cb02.isChecked()) s += cb02.getText().toString();
                if( cb03.isChecked()) s += cb03.getText().toString();
                //if는 만약에라는 뜻으로 만약 cd01이눌러졌다면! s에 값을 담아라!라는뜻이에요

                tv.setText(s); //담기만하면 안되겠죠? textview의 아이디 tv에 그 값을 담아줍니다.

            }
        };
        //위에 만든건 공용이라 생각하시고 위에서 만든 리스너객체를 각 체크박스에 붙이기!
        cb01.setOnCheckedChangeListener(changeListener);
        cb02.setOnCheckedChangeListener(changeListener);
        cb03.setOnCheckedChangeListener(changeListener);


        //토글버튼의 체크상태변경 리스너 생성 해봅시다 토글버튼은 on off를 나타내는 버튼말하는겁니다!
        toggleButton= findViewById(R.id.toggle); //아이디를 가져와야 xml이 연결이되겠죠?
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv.setText( isChecked + "" ); // 눌러서 끌것인가 킬것인가를 설정할수있는 Listener입니다
            }
        });



        //Switch에 체크상태변경 리스너 객체 생성 및 설정
        sw= findViewById(R.id.sw); //슬라이드 하는거있죠? 그거 말하는거에요 바로그거 on off
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv.setText( isChecked +""); //슬라이드해서 글을 넣을껏인가 뺄것인가하는 Listener입니다
            }
        });
    }


}