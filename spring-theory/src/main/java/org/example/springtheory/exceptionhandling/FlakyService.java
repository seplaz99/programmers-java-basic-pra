package org.example.springtheory.exceptionhandling;

import java.sql.SQLException;

public class FlakyService {
    private final int failTimes;
    private int callCount = 0;

    public FlakyService(int failTimes) {
        this.failTimes = failTimes;
    }

    public String fetch() throws SQLException {
        callCount++;
        if (callCount <= failTimes) {
            throw new SQLException("일시적 연결 오류 (호출 " + callCount + ")");
        }
        return "데이터-OK";
    }
}
