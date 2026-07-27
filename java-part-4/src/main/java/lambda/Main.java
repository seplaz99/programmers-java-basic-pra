package lambda;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Operation addAnon = new Operation() {

            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        };

        Operation addLambda = (a, b) -> a + b;

        System.out.println("===== 1. 익명 클래스 vs 람다 (같은 동작) =====");
        System.out.println("익명 클래스 add : " + addAnon.apply(3, 4));
        System.out.println("람다 add : " + addLambda.apply(3, 4));

        System.out.println("\n===== 2. 람다로 만든 연산들 =====");
        Operation add1 = (int a, int  b) -> {return a + b;};
        Operation add2 = (a, b) -> {return a + b;};
        Operation add3 = (a, b) -> a + b;
        Operation sub = (a, b) -> a - b;
        Operation mul = (a, b) -> a * b;
        Operation div = (a, b) -> a / b;

        System.out.println("3 + 4 = " + add3.apply(3, 4));
        System.out.println("9 - 2 = " + sub.apply(9, 2));
        System.out.println("3 * 5 = " + mul.apply(3, 5));
        // System.out.println("3 / 0 = " + div.apply(3, 0));

        System.out.println("\n===== 3. 매개변수 개수별 람다 =====");
        Runnable hello = () -> System.out.println("(0개) 안녕하세요, 람다!");
        hello.run();
        Printer log = msg ->  System.out.println("(1개) [로그] " + msg);
        log.print("시작합니다");
        System.out.println("(2개) 10 + 20 = " + add3.apply(10, 20));

        System.out.println("\n===== 4. 실전: Comparator로 길이순 정렬 =====");
        List<String> list = new ArrayList<>(Arrays.asList("가나다", "가", "라마"));
        System.out.println("정렬 전 : " + list);

        list.sort((s1, s2) -> s1.length() - s2.length());
        System.out.println("정렬 후 : " + list);
    }
}
