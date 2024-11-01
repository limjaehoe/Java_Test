package com.example.javat1application.t_future;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeviceViewModel extends ViewModel {
    private final MutableLiveData<String> status = new MutableLiveData<>("Disconnected");
    private final MutableLiveData<Integer> kvValue = new MutableLiveData<>(0);
    private final DeviceRepository deviceRepository;

    public DeviceViewModel() {
        this.deviceRepository = new DeviceRepository();
    }

    public LiveData<String> getStatus() {
        return status;
    }

    public LiveData<Integer> getKvValue() {
        return kvValue;
    }

    public void connect() {
        try {
            deviceRepository.connect();
            status.setValue("Connected");
        } catch (Exception e) {
            status.setValue("Error: " + e.getMessage());
        }
    }

    public void setKvValue(int value) {
        try {
            deviceRepository.sendKvValue(value);
            kvValue.setValue(value);
        } catch (Exception e) {
            status.setValue("Error: " + e.getMessage());
        }
    }
}