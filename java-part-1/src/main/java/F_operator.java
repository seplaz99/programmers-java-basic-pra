// 연산자
// 산술 연산자 : +, -, *, /, %
// 단항 연사자 : ++, --
// 비교 연산자 : >, <, <=, =>, ==, !=
// 대입 연산자 : =
// 기타 : ?:, 복합대입연산자

// 연산자 우선순위
// 곱셈과 나눗셈은 덧셈과 뺄셈보다 우선순위가 높다.

package main.java;

public class F_operator {
    public static void operExam1() {
        int a = 10;
        int b = 4;

        System.out.printf("%d + %d = %d\n", a, b, a + b);
        System.out.printf("%d - %d = %d\n", a, b, a - b);
        System.out.printf("%d * %d = %d\n", a, b, a * b);
        System.out.printf("%d / %d = %d\n", a, b, a / b);
        System.out.printf("%d %% %d = %f\n", a, b, (float) (a % b));
    }

    public static void operExam2() {
        // 증감연산자 : 피연산자의 값을 1 증가
        // i = i + 1; -> i += 1;
        int i = 5;
        System.out.println("========증감========");
        System.out.println("전위형 : " + ++i);
        System.out.println(i);
        System.out.println("후위형 : " + i++);
        System.out.println(i);
        i = i + 1;
        i += 1;
        System.out.println("i = " + i);

        // 감소연산자 : 피연산자의 값을 1 감소
        // i = i - 1; -> i -= 1;
        i = 5;
        System.out.println("========감소========");
        System.out.println("전위형 : " + --i);
        System.out.println(i);
        System.out.println("후위형 : " + i--);
        System.out.println(i);
        i = i - 1;
        i -= 1;
        System.out.println("i = " + i);
    }

    public static void operExam3() {
        System.out.printf("10 == 10.0f \t %b \n", 10 == 10.0f);
        System.out.printf("'0' == 0 \t %b \n", '0' == 0);
        System.out.printf("'A' == 65 \t %b \n", 'A' == 65);
        System.out.printf("'A' > 'B' \t %b \n", 'A' > 'B');
        System.out.printf("'A' + 1 != 'B' \t %b \n", 'A' + 1 != 'B');
    }

    public static void operExam4() {
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        System.out.println(str1 == str2);
        // &456 -> "Hello" -> str1 &456
        // &789 -> "Hello" -> str2 &789

        String str3 = "Hello";
        String str4 = "Hello";
        System.out.println(str3 == str4);
        // &123 -> "Hello" -> str3변수 &123
        // str4변수 &123

        System.out.println(str1.equals(str2));
        // 두 문자열 비교할 때는 .equals()라는 메소드를 이용해야한다.
    }

    // 논리연산자
    // && 그리고(AND) 모두 true 일때 true
    // || 또는(OR) 중 중 하나만 true여도 true
    // ! 논리 부정 연산 x가 true 일 때 !x는 false
    public static void operExam5() {
        boolean a = true;
        boolean b = false;
        boolean c = true;

        System.out.println("a && b : " + (a && b));
        System.out.println("a && c : " + (a && c));
        System.out.println("a || b : " + (a || b));
        System.out.println("!a : " + !a);
    }

    public static void main(String[] args) {
        operExam3();
    }
}
