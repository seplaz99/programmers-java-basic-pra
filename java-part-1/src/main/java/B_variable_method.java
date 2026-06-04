// 변수 : 값을 저장할 수 있는 메모리상의 공간
// age 공간 20값 -> 주소
// 변수 타입 : 변수에 저장될 값이 어떤 타입인지를 저장하는 값
// 변수 이름 : 변수에 붙인 이름
// 변수 초기화 : 변수를 사용하기 전에 처음으로 값을 저장하는 것

// 변수의 명명 규칙
// 1. 대소문자 구분, 길이 제한 X
// 2. 예약어를 사용해서는 안된다.
// 3. 숫자로 시작하면 안된다.
// 4. 특수문자 '_'와 '$'만을 허용한다.

// 변수의 타입
// 기본형과 참조형
// 기본형 변수는 실제값을 저장
// 논리형(boolean), 문자형(char), 정수형(byte, short, int, long), 실수형(float, double)
// 참조형 변수는 어떤 값이 저장되어 있는 주소값을 저장
// 기본형 제외 나머지 타입

package main.java;

public class B_variable_method {

    // 함수 : 프로그래밍에서 특정 작업을 수행하기 위해 작성된 코드의 묶음
    // 1. 함수 선언 : 함수의 이름과 특성을 정의하는 부분
    // 2. 매개변수(파라미터) : 매개 변수는 함수가 작업을 수행하는 데 필요한 입력값을 전달받는 부분
    // 3. 반환 타입 : 함수가 어떤 유형의 값을 반환할지를 정의

    public static void exam1() {
        // 수행할 내용
        byte myByte = 127;
        System.out.println("byte = " + myByte);

        short myShort = 32767;
        System.out.println("short = " + myShort);

        int num1 = 100;
        System.out.println("num1 = " + num1);
        num1 = 200;
        System.out.println("num1 = " + num1);
        num1 = 300;
        System.out.println("num1 = " + num1);

        long myLong = 1234567890123456789L;
        System.out.println("myLong = " + myLong);

        char myChar = 'a';
        System.out.println("myChar = " + myChar);
        myChar = 66;
        System.out.println("myChar = " + myChar);

        boolean myBoolean = true;
        System.out.println("myBoolean = " + myBoolean);

        final float PI = 3.14f;
        System.out.println("PI = " + PI);
        final double PI2 = 3.1415926535;
        System.out.println("PI2 = " + PI2);

        String myStr = "Hello World";   // 참조형
        System.out.println("myStr = " + myStr);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static int mul(int a, int b) {
        return a * b;
    }

    public static int div(int a, int b) {
        return a / b;
    }

    public static void printresult(int result) {
        System.out.println(result);
        return;
        // return
        // 값을 반환 : 함수가 어떤 값을 계산하거나 처리한 후, 그 결과를 호출한 코드로 돌려줄 때 사용
        // 함수 종료 : return 문이 실행되면 해당 함수는 즉시 종료된다.
    }

    public static void main(String[] args) {
        int result = add(10, 20);
        printresult(result);
        result = sub(10, 20);
        printresult(result);
    }
}
