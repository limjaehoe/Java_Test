package com.example.javat1application.t_mvp.Presenter;

import com.example.javat1application.t_mvp.Model.MvpModel;

public class MvpPresenter implements MvpContract.Presenter {
    MvpContract.View view;
    MvpModel mvpModel;

    public MvpPresenter(MvpContract.View view) {
        this.view = view;

        mvpModel = new MvpModel(this);
    }

    @Override
    public void addNum(int num1, int num2) {
        int answer = num1 + num2;
        view.showResult(answer);
    }

    @Override
    public void subNum(int num1, int num2) {
        int answer = num1 - num2;
        view.showResult(answer);
    }


}
