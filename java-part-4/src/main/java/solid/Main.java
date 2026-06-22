package solid;

public class Main {

    public static void main(String[] args) {
        // Srp
        System.out.println("===== SRP: 단일 책임 =====");
        Journal journal = new Journal();
        journal.add("오늘은 자바를 배웠다");
        journal.add("SOLID는 어렵지만 재밌다");
        new JournalSaver().saveToFile(journal);

        // Ocp
        System.out.println("===== OCP: 개방-폐쇄 =====");
        DiscountPolicy[] policies = {new BasicDiscount(), new GoldDiscount(), new VipDiscount()};
        String[] names = {"일반", "골드", "VIP"};
        for (int i = 0; i < policies.length; i++) {
            System.out.println(names[i] + " 회원 -> " + policies[i].discount(10000) + "원");
        }

        // Lsp
        System.out.println("===== LSP: 리스코프 치환 =====");
        Bird[] bird = {new Sparrow(), new Penguin()};
        for (Bird b : bird) {
            b.eat();
            b.breathe();
        }

        FlyingBird sparrow = new Sparrow();
        Penguin penguin = new Penguin();
        sparrow.fly();
        penguin.swim();

        // Isp
        System.out.println("===== ISP: 인터페이스 분리 =====");
        System.out.print("구형 프린터 : ");
        new SimplePrinter().print();

        SmartPrinter sp = new SmartPrinter();
        System.out.print("복합기 : ");
        sp.print();
        System.out.print("복합기 : ");
        sp.scan();

        // Dip
        System.out.println("===== DIP: 의존관계 역전 =====");
        new NotificationService(new EmailSender()).notifyUser("주문이 완료되었습니다");
        new NotificationService(new SmsSender()).notifyUser("주문이 완료되었습니다");
    }
}
