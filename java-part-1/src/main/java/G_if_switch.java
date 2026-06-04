// 조건문
// 조건식의 연산결과에 따라 실행할 문장이 달라져서 프로그램의 실행흐름을 바꿀 수 있다.

package main.java;

import java.util.Scanner;

public class G_if_switch {
    public static void exam1() {
        int score = 90;
        if (score >= 90) {
            System.out.println("A학점 입니다.");
        }
    }

    public static void exam2() {
        int score = 90;
        if (score >= 60) {
            System.out.println("합격입니다.");
        } else {
            System.out.println("불합격입니다.");
        }
    }

    public static void exam3() {
        Scanner sc = new Scanner(System.in);

        System.out.println("점수를 입력해주세요.");
        int score = sc.nextInt();

        if (score >= 90) {
            System.out.println("A학점 입니다.");
        } else if (score >= 80) {
            System.out.println("B학점 입니다.");
        } else if (score >= 70) {
            System.out.println("C학점 입니다.");
        } else {
            System.out.println("F학점 입니다.");
        }
    }

    public static void main(String[] args) {
        exam3();
    }
}
