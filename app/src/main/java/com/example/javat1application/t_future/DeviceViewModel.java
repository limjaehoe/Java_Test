package com.example.javat1application.t_future;



import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeviceViewModel extends AndroidViewModel {
    private final MutableLiveData<String> status = new MutableLiveData<>("Disconnected");
    private final MutableLiveData<Integer> kvValue = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> maValue = new MutableLiveData<>(0);
    //error livedata
    private final MutableLiveData<DeviceError> error = new MutableLiveData<>();
    private final DeviceRepository deviceRepository;
    //Log
    private final MutableLiveData<List<LogEntry>> logs = new MutableLiveData<>(new ArrayList<>());
    private final DevicePreferences preferences;


    //AndroidViewModel을 상속받아 Application을 생성자로 받음
    public DeviceViewModel(@NonNull Application application) {
        super(application);
        preferences = new DevicePreferences(application);
        deviceRepository = new DeviceRepository();
        loadSavedSettings();  // 생성자에서 호출
    }
    public LiveData<String> getStatus() {
        return status;
    }

    public LiveData<Integer> getKvValue() {
        return kvValue;
    }

    // error getter 추가
    public LiveData<DeviceError> getError() {
        return error;
    }

    public MutableLiveData<Integer> getMaValue() {
        return maValue;
    }
    public LiveData<List<LogEntry>> getLogs() {
        return logs;
    }

    public void connect() {
        try {
            deviceRepository.connect();
            status.setValue("Connected");
        } catch (Exception e) {
            DeviceError connectionError = new DeviceError(
                    DeviceError.ErrorType.CONNECTION_ERROR,
                    "장치 연결에 실패했습니다: " + e.getMessage(),
                    "케이블 연결을 확인하고 다시 시도해주세요."
            );

            error.setValue(connectionError);
            status.setValue(connectionError.getMessage());
        }
    }

    private void addLog(String action, String value) {
        List<LogEntry> currentLogs = logs.getValue();
        if (currentLogs != null) {
            List<LogEntry> newLogs = new ArrayList<>(currentLogs);
            newLogs.add(0, new LogEntry(action, value));
            logs.setValue(newLogs);
        }
    }

    public void setKvValue(int value) {
        try {
            DevicePreferences preferences = new DevicePreferences(getApplication());
            preferences.saveKvValue(value);

            deviceRepository.sendKvValue(value);
            kvValue.setValue(value);
            addLog("KV 설정", String.valueOf(value));
        } catch (IllegalArgumentException e) {
            addLog("KV 설정 실패", e.getMessage());
            DeviceError rangeError = new DeviceError(
                    DeviceError.ErrorType.VALUE_OUT_OF_RANGE,
                    "KV 값이 허용 범위를 벗어났습니다.",
                    "0~100 사이의 값을 입력해주세요."
            );
            error.setValue(rangeError);
        } catch (Exception e) {
            DeviceError communicationError = new DeviceError(
                    DeviceError.ErrorType.COMMUNICATION_ERROR,
                    "값 설정 중 오류가 발생했습니다: " + e.getMessage(),
                    "장치 연결 상태를 확인하고 다시 시도해주세요."
            );
            error.setValue(communicationError);
        }
    }

    public void setMaValue(int value) {  // 추가
        try {
            if (value < 0 || value > 100) {
                throw new IllegalArgumentException("mA value out of range");
            }
            DevicePreferences preferences = new DevicePreferences(getApplication());
            preferences.saveMaValue(value);

            deviceRepository.sendMaValue(value);
            maValue.setValue(value);
            addLog("mA 설정", String.valueOf(value));
        } catch (IllegalArgumentException e) {
            addLog("mA  값이 허용 범위를 벗어남", e.getMessage());
            DeviceError rangeError = new DeviceError(
                    DeviceError.ErrorType.VALUE_OUT_OF_RANGE,
                    "mA 값이 허용 범위를 벗어났습니다.",
                    "0~100 사이의 값을 입력해주세요."
            );
            error.setValue(rangeError);
        } catch (Exception e) {
            addLog("mA 값 설정 중 오류", e.getMessage());
            DeviceError communicationError = new DeviceError(
                    DeviceError.ErrorType.COMMUNICATION_ERROR,
                    "값 설정 중 오류가 발생했습니다: " + e.getMessage(),
                    "장치 연결 상태를 확인하고 다시 시도해주세요."
            );
            error.setValue(communicationError);
        }
    }

    // 저장된 설정값을 불러오는 메소드
    private void loadSavedSettings() {
        try {
            // SharedPreferences에서 저장된 값 불러오기
            int savedKv = preferences.getKvValue();
            int savedMa = preferences.getMaValue();
            boolean wasConnected = preferences.wasConnected();

            // LiveData에 저장된 값 설정
            kvValue.setValue(savedKv);
            maValue.setValue(savedMa);

            // 로그에 기록
            addLog("설정 불러오기", String.format("KV: %d, mA: %d", savedKv, savedMa));

            // 이전에 연결되어 있었다면 자동 연결 시도
            if (wasConnected) {
                connect();
            }
        } catch (Exception e) {
            // 설정 불러오기 실패시 에러 처리
            error.setValue(new DeviceError(
                    DeviceError.ErrorType.COMMUNICATION_ERROR,
                    "설정 불러오기 실패",
                    "앱을 다시 시작해주세요."
            ));
            // 기본값 설정
            kvValue.setValue(0);
            maValue.setValue(0);
            // 로그에 기록
            addLog("설정 불러오기 실패", e.getMessage());
        }
    }
}