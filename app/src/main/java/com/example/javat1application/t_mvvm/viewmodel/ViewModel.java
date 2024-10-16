package com.example.javat1application.t_mvvm.viewmodel;

import android.app.Activity;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.javat1application.R;
import com.example.javat1application.t_mvvm.model.Model;

import java.util.ArrayList;
import java.util.List;
//https://rnflajdrnfl.tistory.com/36
public class ViewModel extends androidx.lifecycle.ViewModel {
    private final Model userRepository = new Model();
    private final MutableLiveData<String> userData = new MutableLiveData<>();

    public LiveData<String> getUserData() {
        return userData;
    }

    public void fetchUserData() {
        userData.setValue(userRepository.getUserData());
    }

}
