package accountbook;

import java.util.*;

public class AccountBookImpl implements AccountBook {
    private Map<String, List<Item>> data = new HashMap<>();
    private Scanner sc;

    public AccountBookImpl(Scanner sc) {
        this.sc = sc;
    }

    // 1. 내역 추가
    @Override
    public void addAccount() {
        System.out.println("날짜 입력 (예 : 2026-06-18)");
        String date = sc.nextLine().trim();

        // 같은 날짜가 이미 있으면 기존 목록에 이어서 추가
        // key가 맵에 있으면 -> 거기에 맵핑된 값을 반환
        // Key가 맵에 없으면 -> 두 번째 인자로 넘긴 값을 반환
        List<Item> list = data.getOrDefault(date, new ArrayList<>());

        while (true) {
            System.out.println("내용 입력");
            String name = sc.nextLine();
            System.out.println("금액 입력");
            int amount = readInt();

            list.add(new Item(name, amount));

            System.out.println("계속 입력하시겠습니까? (y/n)");
            String choice = sc.nextLine().trim();
            if (choice.equals("n")) {
                break;
            }
        }

        data.put(date, list);
        System.out.println("[" + date + "] 내역이 추가되었습니다.");
        printItem(data.get(date));
    }

    @Override
    public void showAccount() {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteItem() {

    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("유효한 숫자를 입력해주세요.");
            }
        }
    }

    private void printItem(List<Item> list) {
        int sum = 0;
        for (Item item : list) {
            System.out.println(item.getName() + " : " + item.getPrice() + "원");
            sum += item.getPrice();
        }
        System.out.println("합계 : " + sum + "원");
    }
}
