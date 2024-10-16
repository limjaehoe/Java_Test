package com.example.javat1application.t_mvp.Model;

import com.example.javat1application.t_mvp.Presenter.MvpContract;

public class MvpModel {

    MvpContract.Presenter presenter;
    public MvpModel(MvpContract.Presenter presenter){
        this.presenter = presenter;
    }

}
