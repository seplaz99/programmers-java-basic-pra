public class B_generic_2<T extends Number> {
    // 변수 타입의 자유를 주지만 제한을 걸어둠

    // add
    public T add(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {   // instanceof : 변수 타입에 속한 변수면 true 반환
            int result = num1.intValue() + num2.intValue(); // num1이 참조형인데 이걸 기본형으로 캐스팅
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.doubleValue() + num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    // sub
    public T sub(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() - num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.doubleValue() - num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    // mul
    public T mul(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() * num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.doubleValue() * num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    // div
    public T div(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() / num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.doubleValue() / num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        B_generic_2<Integer> intCalc = new B_generic_2<>();
        System.out.println("Integer Addition : " + intCalc.add(1, 2));
        System.out.println("Integer Subtraction : " + intCalc.sub(1, 2));
        System.out.println("Integer Multiplication : " + intCalc.mul(1, 2));
        System.out.println("Integer Division : " + intCalc.div(1, 2));

        B_generic_2<Double> doubleCalc = new B_generic_2<>();
        System.out.println("Double Addition : " + doubleCalc.add(1.0, 2.0));
        System.out.println("Double Subtraction : " + doubleCalc.sub(1.0, 2.0));
        System.out.println("Double Multiplication : " + doubleCalc.mul(1.0, 2.0));
        System.out.println("Double Division : " + doubleCalc.div(1.0, 2.0));
    }
}
