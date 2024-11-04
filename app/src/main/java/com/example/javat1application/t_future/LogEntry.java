package com.example.javat1application.t_future;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogEntry {
    private final String timestamp;
    private final String action;
    private final String value;

    public LogEntry(String action, String value) {
        this.timestamp = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        this.action = action;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public String getValue() {
        return value;
    }
}
