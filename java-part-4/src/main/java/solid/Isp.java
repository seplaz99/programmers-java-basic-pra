package solid;

// ❌ 나쁜 예: 인쇄만 하면 되는데 scan/fax까지 강제로 구현
/*interface Machine {
    void print();
    void scan();
    void fax();
}
class SimplePrinter implements Machine {
    public void print() { System.out.println("인쇄"); }
    public void scan()  { throw new RuntimeException("스캔 못 해요"); } // 빈 껍데기
    public void fax()   { throw new RuntimeException("팩스 못 해요"); }
}*/

interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Faxer {
    void fax();
}

class SimplePrinter implements Printer {
    @Override
    public void print() {
        System.out.println("인쇄만 합니다");
    }
}

class SmartPrinter implements Printer, Scanner {
    @Override
    public void print() {
        System.out.println("인쇄");
    }

    @Override
    public void scan() {
        System.out.println("스캔");
    }
}