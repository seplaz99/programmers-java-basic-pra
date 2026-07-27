
// 반복문
// 어떤 작업이 반복적으로 수행되도록 할 때 사용된다.

package main.java;

import java.util.Scanner;

public class H_loop {
    /*
        for 문
        for ( 초기값; 조건식; 증감식 ) {
            // 조건식이 참일 때 수행될 문장들을 적는다.
        }
     */
    public static void exam1() {
        for ( int i = 0; i < 10; i++) {
            System.out.println("cnt : " + i);
        }
    }

    public static void exam2() {
        for ( int i = 1; i < 10; i++ ) {
            System.out.println("2 * " + i + " = " + (2 * i));
        }
    }

    // 중첩
    public static void exam3() {
        for ( int i = 1; i < 3; i++ ) {
            System.out.println("i= " + i);
            for ( int k = 1; k < 5; k++ ) {
                System.out.println("k= " + k);
            }
        }
    }

    // 구구단 : 2단부터 9단까지 중첩for문을 사용해서
    public static void practice1() {
        System.out.println("=== 구구단 전체 ===");
        for ( int dan = 2; dan < 10; dan++ ) {
            System.out.println("=== " + dan + "단 ===");
            for ( int k = 1; k < 10; k++ ) {
                System.out.println(dan + " * " + k + " = " + (dan * k));
            }
        }
    }

    // continue : 1 ~ 100까지 짝수만 출력
    public static void exam4() {
        for ( int i = 1; i <= 100; i++ ) {
            if ( i % 2 != 0 ) {
                // 홀수
                continue;
            }
            // 짝수
            System.out.println(i);
        }
    }

    // 홀수만 출력
    public static void practice2() {
        for ( int i = 1; i <= 100; i++ ) {
            if ( i % 2 == 0 ) {
                // 짝수
                continue;
            }
            // 홀수
            System.out.println(i);
        }
    }

    // break : 1 ~ 100까지 올라가는데, 30에 도달했을 때 멈춤(탈출)
    public static void exam5() {
        for ( int num = 1; num <= 100; num++ ) {
            if ( num == 30 ) {
                break;
            }
            System.out.println(num);
        }

        System.out.println("탈출한다");
    }

    public static void exam6() {

        for ( int i = 0; i < 3; i++ ) {
            System.out.println(" - 첫 번째 루프 : " + i);
            for ( int j = 0; j < 3; j++ ) {
                System.out.println(" - 두 번째 루프 : " + j);
                for ( int k = 0; k < 3; k++ ) {
                    System.out.println(" - 세 번째 루프 : " + k);
                }
            }
        }

    }

    public static void exam7() {
        int i = 0;
        for (; i < 3; ) {
            // 수행
            System.out.println(i++);
        }
    }

    public static void exam8() {
        int i = 0;
        for (;;) {
            if ( i > 10 ) break;
            System.out.println(++i);
        }
    }

    public static void exam9() {
        // 무한 루프
        int i = 0;
        for (;true;) {
            System.out.println(i++);
        }
    }

    public static void exam10() {
        for (int i = 9; i >= 0; i--) {
            System.out.println(i);
        }
    }

    // 구구단을 9단부터 2단까지 역으로 출력해주세요.
    public static void practice3() {

        for ( int dan = 9; dan > 1; dan-- ) {
            System.out.println("dan = " + dan);
            for ( int i = 9; i >= 1; --i) {
                System.out.println(dan + " * " + i + " = " + (dan * i));
            }
        }

    }

    // while문
    /*
        while (조건식) {
            // 조건식의 연산결과가 참(true)인 동안, 반복될 문장을 적는다.
        }
     */
    public static void exam11() {
        int cnt = 0;
        while ( cnt <= 10 ) {
            System.out.println("cnt = " + cnt);
            cnt++;
        }
    }

    public static void practice4() {
        System.out.println("구구단 2단 출력");
        int i = 1;
        while ( i < 10 ) {
            System.out.println(" 2 * " + i + " = " + (2 * i));
            i++;
        }
    }


    // while 중첩가능 -> 전체 구구단 출력
    public static void practice5() {
        int dan = 2;
        while ( dan < 10 ) {
            System.out.println("[" + dan + "단]");
            int i = 1;
            while ( i < 10 ) {
                System.out.println(dan + " * " + i + " = " + (dan * i));
                i++;
            }
            dan++;
        }
    }

    public static void exam12() {
        int i = 3;
        while ( true ) {
            if ( i == 0 ) break;
            System.out.println(i--);
        }
    }

    public static void exam13() {
        int i = 10;
        while ( --i > 0 ) {
            System.out.println(i);
        }

        System.out.println("=========");
        i = 10;
        while ( i-- > 0 ) {
            System.out.println(i);
        }
    }

    // 무한루프 : while(true)
    // 사용자로부터 정수값을 입력받아 합계를 내도록 구현하시요.
    // ex) 사용자 : 10입력 -> 10 + 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 55
    // 결과 : 55 출력 하고
    // 다시 정수값을 입력받아 합계를 낸다.
    // 사용자가 0을 입력하면 종료한다.
    public static void practice6() {
        // 사용자한테 정수 값을 입력받는다.
        // 무한루프를 건다.
        // 합계 로직을 구현한다.
        // 0을 누르면 탈출하는 조건을 넣어준다.
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("정수를 입력하세요 : (종료를 원하면 0을 입력하세요)");
            int num = sc.nextInt();

            if (num == 0) {
                System.out.println("프로그램을 종료합니다");
                break;
            }

            int sum = 0;

            for (int i = 1; i <= num; i++) {
               sum += i;
            }

            System.out.println("결과 : " + sum);
        }
    }

    public static void practice7() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("원하는 단을 입력하세요 : (종료를 원하면 0을 입력하세요)");
            int num = sc.nextInt();

            if (num == 0) {
                System.out.println("프로그램을 종료합니다");
                break;
            }

            for (int i = 1; i <= 9; i++) {
                System.out.println(num + " * " + i + " = " + (num * i));
            }
        }
    }

    public static void exam14() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("정수를 입력하세요 : (종료를 원하면 0을 입력하세요)");
            int num = sc.nextInt();

            if (num == 0) {
                System.out.println("프로그램을 종료합니다");
                break;
            }

            for (int i = 1; i <= num; i++) {
                if (i % 2 == 0) {
                    System.out.println(num);
                }
            }
        }
    }


    public static void main(String[] args) {
        practice7();
    }
}
