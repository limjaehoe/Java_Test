package com.example.javat1application.t_mvvm.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.javat1application.MainActivity;
import com.example.javat1application.R;
import com.example.javat1application.databinding.ActivityMvvmBinding;

public class MvvmActivity extends AppCompatActivity {
    private ActivityMvvmBinding binding;
    private ViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_mvvm);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);



    }
}