package com.example.javat1application.t_future;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeviceViewModel extends ViewModel {
    private final MutableLiveData<String> status = new MutableLiveData<>("Disconnected");
    private final MutableLiveData<Integer> kvValue = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> maValue = new MutableLiveData<>(0);
    //error livedata
    private final MutableLiveData<DeviceError> error = new MutableLiveData<>();
    private final DeviceRepository deviceRepository;
    //Log
    private final MutableLiveData<List<LogEntry>> logs = new MutableLiveData<>(new ArrayList<>());


    public DeviceViewModel() {
        this.deviceRepository = new DeviceRepository();
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
}