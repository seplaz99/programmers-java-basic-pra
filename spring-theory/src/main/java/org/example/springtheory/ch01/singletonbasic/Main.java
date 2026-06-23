package org.example.springtheory.ch01.singletonbasic;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== 1. 싱글톤 없이: 번호표 두 대 (버그!) =====");

        NaiveTicketMachine a = new NaiveTicketMachine();
        NaiveTicketMachine b = new NaiveTicketMachine();

        System.out.println("A 기계가 발급: " + a.issue() + "번");
        System.out.println("B 기계가 발급: " + b.issue() + "번  <- 중복!");
        System.out.println("A 기계가 발급: " + a.issue() + "번");
        System.out.println("B 기계가 발급: " + b.issue() + "번  <- 또 중복!");

        System.out.println("\n===== 2. 싱글톤 적용: 번호표는 하나뿐 =====");

        // new TicketMachine(); 오류 발생

        // 여러 창구에서 각자 인스턴스를 요청하지만 모두 같은 객체를 가리킴
        TicketMachine w1 = TicketMachine.getInstance();
        TicketMachine w2 = TicketMachine.getInstance();
        TicketMachine w3 = TicketMachine.getInstance();

        System.out.println("1번 창구가 발급: " + w1.issue() + "번");
        System.out.println("2번 창구가 발급: " + w2.issue() + "번");
        System.out.println("1번 창구가 발급: " + w1.issue() + "번");
        System.out.println("3번 창구가 발급: " + w3.issue() + "번");

        // 두 변수가 가리키는 실제 메모리 주소(참조값)가 같은지 비교
        System.out.println("같은 기계인가? " + (w1 == w2));

        // 3. Lazy 싱글톤 적용
        System.out.println("\n===== 3. lazy 초기화 (설정 관리자) =====");
        Settings s1 = Settings.getInstance();
        System.out.println("앱 설정 - 테마: " + s1.getTheme());

        Settings s2 = Settings.getInstance();
        System.out.println("앱 설정 - 테마: " + s2.getTheme() + " (어디서 불러도 같은 설정)");
        System.out.println("같은 설정 객체인가? " + (s1 == s2));
    }
}
