package com.example.javat1application.t_future;

public class DeviceRepository { //데이터 접근과 장치 통신을 담당
    private final SerialCommunicator serialCommunicator;
    private boolean isConnected = false;

    public DeviceRepository() {
        this.serialCommunicator = new SerialCommunicator();
    }

    public void connect() throws DeviceException {
        try {
            serialCommunicator.connect();
            isConnected = true;
        } catch (Exception e) {
            throw new DeviceException("Connection failed: " + e.getMessage());
        }
    }

    public void sendKvValue(int value) throws DeviceException {
        if (!isConnected) {
            throw new DeviceException("Device not connected");
        }

        try {
            serialCommunicator.sendCommand("SET_KV:" + value);
        } catch (Exception e) {
            throw new DeviceException("Failed to send KV value: " + e.getMessage());
        }
    }

    public void sendMaValue(int value) throws DeviceException {
        if (!isConnected) {
            throw new DeviceException("Device not connected");
        }

        try {
            serialCommunicator.sendCommand("SET_MA:" + value);
        } catch (Exception e) {
            throw new DeviceException("Failed to send mA value: " + e.getMessage());
        }
    }
}