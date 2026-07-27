// String 클래스의 특징
// 불변성 (Immutable)
// String 객체가 생성되면 그 객체의 문자열 값은 변경할 수 없다.
// 문자열을 수정하려면 새로운 String 객체를 생성해야 한다.
// 메모리 효율성
// 같은 값을 가진 String 리터럴은 같은 메모리에서 공유한다.

package main.java;

public class L_string {

    // charAt(int idx)
    // 문자열에서 특정 위치에 있는 문자(char)을 반환
    public static void exam1() {
        String str = "Hello";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println(c);
        }
    }

    // subString(int beginIdx, int endIdx)
    // 문자열의 특정 부분을 추출하여 반환
    public static void exam2() {
        String str = "algorithm";
        String sub = str.substring(0, 5);   // beginIdx 포함, endIdx 포함하지 않음 -> 0~4 algo

        System.out.println(sub);
    }

    // split(String regex)
    // 주어진 정규 표현식을 기준으로 문자열을 분리하여 배열로 반환
    public static void exam3() {
        String str = "one,two,three";
        String[] parts = str.split(",");

        for (String p : parts) {
            System.out.println(p);
        }
    }

    // toCharArray()
    // 문자열을 문자 배열로 반환
    public static void exam4() {
        String str = "hello";
        char[] chars = str.toCharArray();

        for (char c : chars) {
            System.out.println(c);
        }
    }

    // equals(String anotherString)
    // 두 문자열의 값을 비교하여 같으면 true, 다르면 false를 반환
    public static void exam5() {
        String str1 = "hello1";
        String str2 = "hello1";
        System.out.println(str1.equals(str2));
    }

    // contains(CharSequence s)
    // 문자열이 특정 문자열을 포함하고 있는지 확인
    public static void exam6() {
        String str = "hello";
        System.out.println(str.contains("ell"));
    }

    // replace(char oldChar, char newChar) 및 replace(CharSequence target, CharSequence replacement)
    // 문자열 내의 특정 문자 또는 문자열을 다른 문자 또는 문자열로 대체
    public static void exam7() {
        String str = "hello";
        String newStr = str.replace('l', 'L');
        System.out.println(newStr);
    }

    // indexOf(String str) 및 lastIndexOf(String str)
    // 특정 문자열이 처음 또는 마지막으로 나타나는 위치를 반환
    public static void exam8() {
        String str = "hello";
        int idx = str.indexOf("l"); // 2
        int lastIdx = str.lastIndexOf("l"); // 3
        System.out.println(idx);
        System.out.println(lastIdx);
    }

    // StringBuilder 및 StringBuffer
    // StringBuilder와 StringBuffer는 가변(mutable) 문자열
    // StringBuilder는 성능이 우수하며, StringBuffer는 스레드 안전(thread-safe)한 버전
    public static void exam9() {
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        sb.append("world");
        String result = sb.toString();
        System.out.println(result);
    }

    // reverse()
    // 문자열을 뒤집습니다. String 자체는 reverse() 메서드가 없지만, StringBuilder를 사용하여 문자를 뒤집을 수 있다.
    public static void exam10() {
        StringBuilder sb = new StringBuilder("hello");
        String result = sb.reverse().toString();
        System.out.println(result);
    }

    // compareTo(String anotherString)
    // 두 문자열을 사전적으로 비교하여, 현재 문자열이 더 작으면 -1, 같으면 0, 더 크면 1을 반환합니다.
    public static void exam11() {
        String str1 = "apple";
        String str2 = "banana";

        int result = str1.compareTo(str2);
        System.out.println(result);
    }

    // toLowerCase(), toUpperCase()
    // 문자열을 소문자 또는 대문자로 변환
    public static void exam12() {
        String str = "Hello";
        String lower = str.toLowerCase();
        String upper = str.toUpperCase();
        System.out.println(lower);
        System.out.println(upper);
    }

    public static void main(String[] args) {
        exam1();
    }
}
