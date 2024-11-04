package com.example.javat1application.t_future;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.javat1application.R;
import com.example.javat1application.databinding.ActivityGenerTestMainBinding;

public class GenerTestMainActivity extends AppCompatActivity {

    private ActivityGenerTestMainBinding binding;  // ViewBinding 선언
    private DeviceViewModel viewModel;
    private LogAdapter logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityGenerTestMainBinding.inflate(getLayoutInflater());

        // ViewModel 초기화
        viewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
        setContentView(binding.getRoot());

        setupUI();
        setupLogRecyclerView();
        observeViewModel();

    }

    private void setupUI() {
        binding.connectButton.setOnClickListener(v -> {
            binding.connectButton.setEnabled(false);
            viewModel.connect();
        });

        // KV Up 버튼
        binding.btnKvUp.setOnClickListener(v -> {
            int currentValue = binding.kvControl.getProgress();
            if(currentValue < 100) {  // 최대값 체크
                viewModel.setKvValue(currentValue + 1);
            }
        });

        // KV Down 버튼
        binding.btnKvDown.setOnClickListener(v -> {
            int currentValue = binding.kvControl.getProgress();
            if(currentValue > 0) {  // 최소값 체크
                viewModel.setKvValue(currentValue - 1);
            }
        });


        binding.kvControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        // mA 컨트롤 설정
        binding.btnMaUp.setOnClickListener(v -> {
            int currentValue = binding.maControl.getProgress();
            if(currentValue < 100) {
                viewModel.setMaValue(currentValue + 1);
            }
        });

        binding.btnMaDown.setOnClickListener(v -> {
            int currentValue = binding.maControl.getProgress();
            if(currentValue > 0) {
                viewModel.setMaValue(currentValue - 1);
            }
        });

        binding.maControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    viewModel.setMaValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    private void observeViewModel() {

        // 1. 장비 연결 상태 관찰
        viewModel.getStatus().observe(this, status -> {
            binding.statusText.setText(status); // 상태 텍스트 업데이트 ("Connected" 또는 "Disconnected" 또는 에러 메시지)
            binding.connectButton.setEnabled(status.startsWith("Disconnected")); // 연결 버튼 활성화/비활성화 (연결 해제 상태일 때만 버튼 활성화)
        });

        // 2. kV 값 변경 관찰
        viewModel.getKvValue().observe(this, value -> {
            binding.kvValue.setText(String.format("KV: %d", value)); // kV 값 텍스트 표시 (예: "KV: 50")
            binding.kvControl.setProgress(value); // SeekBar 위치 업데이트

            // kV 증감 버튼 상태 관리
            binding.btnKvUp.setEnabled(value < 100);   // 100 미만일 때만 증가 버튼 활성화
            binding.btnKvDown.setEnabled(value > 0);   // 0 초과일 때만 감소 버튼 활성화
        });

        // 3. mA 값 변경 관찰
        viewModel.getMaValue().observe(this, value -> {
            binding.maValue.setText(String.format("mA: %d", value)); // mA 값 텍스트 표시 (예: "mA: 50")
            binding.maControl.setProgress(value);// SeekBar 위치 업데이트
            // mA 증감 버튼 상태 관리
            binding.btnMaUp.setEnabled(value < 100);   // 100 미만일 때만 증가 버튼 활성화
            binding.btnMaDown.setEnabled(value > 0);   // 0 초과일 때만 감소 버튼 활성화
        });

        // 4. 에러 상태 관찰
        viewModel.getError().observe(this, this::showErrorDialog);
        // 에러 발생시 다이얼로그 표시

        // 로그 observer 추가
        viewModel.getLogs().observe(this, logs -> {
            logAdapter.setLogs(logs);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;  // 메모리 누수 방지
    }

    private void showErrorDialog(DeviceError error) {
        new AlertDialog.Builder(this)
                .setTitle(error.getType().getDescription())
                .setMessage(error.getMessage() + "\n\n해결방법: " + error.getSolution())
                .setPositiveButton("확인", null)
                .show();
    }

    private void setupLogRecyclerView() {
        logAdapter = new LogAdapter();
        binding.logRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        binding.logRecyclerView.setAdapter(logAdapter);

        // 아이템 구분선 추가
        binding.logRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}