package com.example.javat1application.t_askcode;

import android.util.Log;

import java.util.Arrays;

public class PacketLengthUtil {
    // ASCII 문자열을 int로 변환하는 메서드
    public static int convertPacketLength(byte[] data) {
        if (data == null || data.length < 4) {
            return -1; // 유효하지 않은 데이터
        }

        // ASCII 문자를 문자열로 변환
        String lengthStr1 = Arrays.toString(new int[]{data[2] & 0XFF});
        String lengthStr2 = Arrays.toString(new int[]{data[3] & 0XFF});
        String lengthStr = String.format("%c%c", lengthStr1, lengthStr2);


        try {
            // 16진수 문자열을 int로 변환
            return Integer.parseInt(lengthStr, 16);
        } catch (NumberFormatException e) {
            return -1; // 변환 실패
        }
    }

}
