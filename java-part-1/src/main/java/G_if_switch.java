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

    public static void exam4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("점수를 입력해주세요.");
        int score = sc.nextInt();

        if (score >= 90) {
            if (score >= 95) {
                System.out.println("A+학점 입니다.");
            }
            System.out.println("A학점 입니다.");
        } else if (score >= 80) {
            System.out.println("B학점 입니다.");
        } else if (score >= 70) {
            System.out.println("C학점 입니다.");
        } else {
            System.out.println("F학점 입니다.");
        }
    }

    public static void exam5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("음료수 번호를 누르세요");
        System.out.println("[1] 콜라 [2] 사이다 [3] 환타 [4] 물");

        int menuNum = sc.nextInt();

        switch (menuNum) {
            case 1:
                System.out.println("주문하신 콜라가 나왔습니다");
                break;
            case 2:
                System.out.println("주문하신 사이다가 나왔습니다");
                break;
            case 3:
                System.out.println("주문하신 환타가 나왔습니다");
                break;
            case 4:
                System.out.println("주문하신 물이 나왔습니다");
                break;
            default:
                System.out.println("잘못된 번호를 누르셨습니다");
        }
    }

    public static void exam6() {
        Scanner sc = new Scanner(System.in);

        System.out.println("학점을 입력하세요");

        String score = sc.nextLine();

        String grade = switch (score) {
            case "A" -> "90점 이상입니다";
            case "B" -> "80점 이상입니다";
            case "C" -> "70점 이상입니다";
            default -> "70점 미만입니다";
        };

        System.out.print(grade);
    }

    public static void main(String[] args) {
        exam6();
    }
}
