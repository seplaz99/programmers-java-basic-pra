// 배열
// 같은 타이브이 여러 변수를 하나의 묶음으로 다루는 것
// 변수와 달리 배열은 각 저장공간이 연속으로 배치

package main.java;

public class J_array_2 {

    public static void exam1() {
        int[][] scores = new int[3][2];

        scores[0][0] = 10;
        scores[0][1] = 20;
        scores[1][0] = 30;
        scores[1][1] = 40;
        scores[2][0] = 50;
        scores[2][1] = 60;

        System.out.println(scores.length);
        System.out.println(scores[0].length);

        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores[i].length; j++) {
                System.out.print(scores[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void exam2() {
        int[][] scores = {{10, 20}, {30, 40}, {50, 60}};

        for ( int i = 0; i < scores.length; i++ ) {
            for (int j = 0; j < scores[i].length; j++) {
                System.out.println(scores[i][j]);
            }
        }
    }

    // 가변 배열
    // 2차원 이상의 다차원 배열을 생성할 때 전체 배열 차수 중 마지막 차수의 길이를 지정하지 않고,
    // 추후에 각기 다른 길이의 배열을 생성함으로써 고정된 형태가 아닌 보다 유동적인 가변 배열을 구성

    public static void exam3() {
        int[][] scores = new int[2][];

        scores[0] = new int[] {1, 2};
        scores[1] = new int[3];

        scores[1][0] = 10;
        scores[1][1] = 20;
        scores[1][2] = 30;
    }

    public static void exam4() {
        int[][][] scores = new int[2][2][2];
        int[][][] scores2 = new int[2][][];
    }

    // 역으로 출력하세요.
    // 단 내장함수 -> 인덱스(번호)로 직접 접근
    public static void practice1() {
        char[] chars = {'a', 'b', 'c', 'd', 'e'};

        int len = chars.length - 1;

        for ( int i = len; i >= 0; i--) {
            System.out.println(i + " : " + chars[i]);
        }

    }

    public static void exam5() {
        String[] words = {"apple", "banana", "orange"};

        for ( String word : words ) {
            System.out.println(word);
        }
    }

    public static void exam6() {
        int[] scores = {10, 20, 30};
        int num = 20;

        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }

        exam6_sub(scores, num);
        System.out.println("=======");
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }
        System.out.println("=======");
        System.out.println(num);
    }

    // int[] -> new -> 참조형 &123 -> call by reference
    // int num -> 기본형 20 -> call by value
    public static int exam6_sub(int[] scores, int num) {
        scores[1] = 90;
        num = 90;
        return num;
    }

    public static void main(String[] args) {
        exam1();
    }
}
