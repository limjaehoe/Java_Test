package com.example.javat1application.t_future;

public class DeviceRepository {
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
}