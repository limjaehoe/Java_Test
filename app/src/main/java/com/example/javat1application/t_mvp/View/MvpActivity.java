package com.example.javat1application.t_mvp.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;
import com.example.javat1application.t_mvp.Presenter.MvpContract;
import com.example.javat1application.t_mvp.Presenter.MvpPresenter;

//https://heegs.tistory.com/114
//https://stickode.tistory.com/255
public class MvpActivity extends AppCompatActivity implements MvpContract.View
{
    private EditText number1;		//입력할 EditText
    private EditText number2;		//입력할 EditText
    private Button sumButton;
    private Button subButton;
    private MvpContract.Presenter presenter;	//presenter와 통신하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mvp);


        presenter = new MvpPresenter(this);
        init();
    }

    private void init(){
        sumButton = (Button)findViewById(R.id.sum);
        number1 = (EditText)findViewById(R.id.number1);
        number2 = (EditText)findViewById(R.id.number2);

        //버튼 클릭
        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //결과 값 계산
                if(Integer.parseInt(number1.getText().toString()) > Integer.parseInt(number2.getText().toString())){

                    presenter.subNum(Integer.parseInt(number1.getText().toString()), Integer.parseInt(number2.getText().toString()));
                }
                else{
                    presenter.addNum(Integer.parseInt(number1.getText().toString()), Integer.parseInt(number2.getText().toString()));
                }
            }
        });
    }


    @Override
    public void showResult(int answer) {
        ((TextView)findViewById(R.id.result)).setText(Integer.toString(answer));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}