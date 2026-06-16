// 예외처리
// 프로그램 실행 중에 발생할 수 있는 오류(예외)를 처리하며, 프로그램이 갑자기 멈추지 않도록 하는 방법
// 예외가 발생해도 프로그램이 정상적으로 흐름을 이어가거나, 안전하게 마무리할 수 있게 해준다.

// * 예외(Exception)의 종류
//  •  Checked Exception: 컴파일 시점에 처리(try-catch 또는 throws)를 강제하는 예외이다. (예: IOException, FileNotFoundException)
//  •  Unchecked Exception: 실행 중에 발생하며 처리를 강제하지 않는 예외이다. (RuntimeException 계열, 예: NullPointerException, ArithmeticException)

// * 기본 구조
//  •  try: 예외가 발생할 수 있는 코드를 작성하는 블록이다.
//  •  catch: try에서 예외가 발생하면 그 예외를 잡아 처리하는 블록이다.
//  •  finally: 예외 발생 여부와 상관없이 항상 실행되는 블록이다. (주로 자원 정리에 사용)

public class B_try_catch {

    // 1. 기본 try-catch
    public static void exam1() {
        try {
            int a = 10;
            int b = 0;
            int result = a / b; // 0으로 나누면 예외 발생
            System.out.println("Result : " + result);
        } catch (ArithmeticException e) {
            // 발생한 예외를 e로 받아서 처리
            System.out.println("예외 발생 : 0으로 나눌 수 없습니다.");
            System.out.println("예외 메시지 : " + e.getMessage());
        }

        System.out.println("프로그램은 계속 실행합니다.");
    }

    // 2. 멀티 catch
    public static void exam2() {
        try {
            int[] arr = new int [5];
            arr[9] = 10;
        } catch (ArithmeticException e) {
            System.out.println("산술 예외 처리");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 예외 처리 : " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("null 참조 예외 처리 : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("그 외 예외 처리 : " + e.getMessage());
        }
    }

    // 2-2. 멀티 catch
    // 처리 방식이 같은 여러 예외는 |(파이프)로 묶어 하나의 catch에서 처리할 수 있다. (Java 7 이상)
    public static void exam2_2() {
        try {
            String text = null;
            System.out.println(text.length()); // NullPointerException 발생
            System.out.println(10 / 0); // ArithmeticException 발생
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("Null 이거나 산술 예외 발생 : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        exam2_2();
    }
}
