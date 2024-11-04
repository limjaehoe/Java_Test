package com.example.javat1application.t_future;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogEntry {
    private final String timestamp; //시간
    private final String action; // 동작
    private final String value; //값

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



/*
Entry는 '입력' 또는 '기입'이라는 의미를 가진 단어입니다.
컴퓨터 프로그래밍에서 Entry는 주로:

데이터베이스의 한 행(row)
로그 기록의 한 항목
목록(리스트)의 한 항목
을 의미할 때 자주 사용됩니다.
 */