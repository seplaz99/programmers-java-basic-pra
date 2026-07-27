// 배열
// 같은 타이브이 여러 변수를 하나의 묶음으로 다루는 것
// 변수와 달리 배열은 각 저장공간이 연속으로 배치

package main.java;

public class J_array {

    public static void exam1() {
        int[] scores = new int[3]; // 0, 1, 2

        scores[0] = 10;
        scores[1] = 20;
        scores[2] = 30;

        System.out.println(scores);
        System.out.println(scores[0]);
        System.out.println(scores[1]);
        System.out.println(scores[2]);
    }

    public static void exam2() {
        int[] scores = {10, 20, 30};
        System.out.println("len : " + scores.length);

        // 0, 1, 2
        for ( int i = 0; i < scores.length; i++ ) {
            System.out.println(scores[i]);
        }
    }

    public static void exam3() {
        int[] scores = new int[3]; // 0, 1, 2

        // 값 초기화
        for ( int i = 1; i <= scores.length; i++ ) {
            scores[i-1] = i * 10;
        }

        for ( int s : scores ) {
            System.out.println(s);
        }

    }

    public static void exam4() {
        char[] chars = {'a', 'b', 'c', 'd', 'e'};

        for ( char c : chars ) {
            System.out.println(c);
        }

    }

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
        exam6();
    }
}
