package com.example.javat1application.t_future;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.javat1application.R;

public class GenerTestMainActivity extends AppCompatActivity {
    private DeviceViewModel viewModel;
    private TextView statusText;
    private TextView kvValue;
    private Button connectButton;
    private SeekBar kvControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gener_test_main);

        // View 초기화
        statusText = findViewById(R.id.statusText);
        kvValue = findViewById(R.id.kvValue);
        connectButton = findViewById(R.id.connectButton);
        kvControl = findViewById(R.id.kvControl);

        // ViewModel 초기화
        viewModel = new ViewModelProvider(this).get(DeviceViewModel.class);

        setupUI();
        observeViewModel();

    }

    private void setupUI() {
        connectButton.setOnClickListener(v -> {
            connectButton.setEnabled(false);
            viewModel.connect();
        });

        kvControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    viewModel.setKvValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void observeViewModel() {
        viewModel.getStatus().observe(this, status -> {
            statusText.setText(status);
            connectButton.setEnabled(status.startsWith("Disconnected"));
        });

        viewModel.getKvValue().observe(this, value -> {
            kvValue.setText(String.format("KV: %d", value));
            kvControl.setProgress(value);
        });
    }
}