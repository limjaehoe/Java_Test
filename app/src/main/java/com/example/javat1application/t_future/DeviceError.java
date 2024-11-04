package com.example.javat1application.t_future;

/*
에러 타입을 Enum으로 명확하게 정의
각 에러에 대한 설명과 해결방법 포함
ViewModel에서 적절한 에러 객체 생성
Activity에서 다이얼로그로 에러 표시
 */
public class DeviceError {
    // 에러 타입 정의
    public enum ErrorType {
        CONNECTION_ERROR("연결 오류"),
        COMMUNICATION_ERROR("통신 오류"),
        VALUE_OUT_OF_RANGE("값 범위 초과"),
        HARDWARE_ERROR("하드웨어 오류");

        private final String description;

        ErrorType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private final ErrorType type;
    private final String message;
    private final String solution;

    // 생성자
    public DeviceError(ErrorType type, String message, String solution) {
        this.type = type;
        this.message = message;
        this.solution = solution;
    }

    // Getter 메소드들
    public ErrorType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getSolution() {
        return solution;
    }

    // 에러 메시지 포맷팅
    @Override
    public String toString() {
        return String.format("Error: %s\nMessage: %s\nSolution: %s",
                type.getDescription(), message, solution);
    }
}