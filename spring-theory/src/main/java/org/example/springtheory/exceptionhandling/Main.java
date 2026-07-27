package org.example.springtheory.exceptionhandling;

public class Main {

    public static void main(String[] args) {
        FileLogger logger = new FileLogger();
        DataService service = new DataService(logger);

        System.out.println("===== 1) 예외 복구: 재시도 (3번째에 성공) =====");
        try {
            String result = service.fetchWithRetry(new FlakyService(2));
            System.out.println("최종 결과 : " + result);
        } catch (RuntimeException e) {
            System.out.println("실패 통보 : " + e.getMessage());
        }

        System.out.println("\n===== 2) 예외 복구: 재시도 모두 실패 -> 통보 =====");
        try {
            service.fetchWithRetry(new FlakyService(99));
        } catch (RuntimeException e) {
            System.out.println("실패 통보 : " + e.getMessage());
        }

        System.out.println("\n===== 3) 예외 전환: 아이디 중복 -> 의미 있는 예외 =====");
        try {
            service.registerUser("kim");
        } catch (DataService.DuplicateUserIdException e) {
            System.out.println("잡힘 : " + e.getMessage());
            System.out.println("원인 보존 : " + e.getCause());
        }

        System.out.println("\n===== 로그 파일 위치 =====");
        System.out.println(logger.getLogFilePath());
    }
}
