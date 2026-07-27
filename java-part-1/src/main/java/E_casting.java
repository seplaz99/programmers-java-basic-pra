// 형변환
// 변수 또는 상수의 타입을 다른 타입으로 변환하는 것
// 기본형에서 boolean을 제외한 나머지 타입들은 서로 형변환이 가능하다.

// 암시적(자동) 형변환 / 명시적(강제) 형변환
// 암시적 형변환 : 더 작은 자료형이 더 큰 자료형으로 변환할 때 자동으로 이루어진다.
// 묵시적 형변환 : 더 큰 자료형을 더 작은 자료형으로 변환할 때 명시적으로 해야 한다.

package main.java;

public class E_casting {
    public static void main(String[] args) {
        double d = 3.14;
        System.out.println(d);
        int score = (int) d;
        System.out.println(score);

        int n = 65;
        System.out.println(n);
        char c = (char) n;
        System.out.println(c);

        char c2 = 'A';
        System.out.println(c2);
        int n2 = c2;
        System.out.println(n2);

        float f = 3.14f;
        System.out.println(f);
        int n3 = (int) f;
        System.out.println(n3);

        int n4 = 3;
        System.out.println(n4);
        float f2 = n4;
        System.out.println(f2);



    }
}
