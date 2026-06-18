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

    // 2. 내역 조회
    @Override
    public void showAccount() {
        if (data.isEmpty()) {
            System.out.println("기록이 없습니다.");
            return;
        }

        System.out.println("=== 기록된 날짜 ===");
        List<String> dates = new ArrayList<>(data.keySet());
        Collections.sort(dates, Collections.reverseOrder());    // 최신순
        for (String d : dates) {
            System.out.println(" - " + d);
        }

        System.out.println("조회할 날짜 입력 (예 : 2026-06-18)");
        String date = sc.nextLine().trim();
        if (!data.containsKey(date)) {
            System.out.println("해당 날짜의 내역이 없습니다.");
            return;
        }

        System.out.println("[" + date + "] 내역:");
        printItem(data.get(date));
    }

    // 3. 해당 날짜의 모든 내역 삭제
    @Override
    public void deleteAll() {
        System.out.println("삭제할 날짜 입력 (예 : 2026-06-18)");
        String date = sc.nextLine().trim();
        if (!data.containsKey(date)) {
            System.out.println("해당 날짜의 내역이 없습니다.");
            return;
        }

        data.remove(date);
        System.out.println("[" + date + "] 내역이 삭제되었습니다.");
    }

    // 4. 해당 날짜의 특정 내역 삭제
    @Override
    public void deleteItem() {
        System.out.println("삭제할 날짜 입력 (예 : 2026-06-18)");
        String date = sc.nextLine().trim();
        if (!data.containsKey(date)) {
            System.out.println("해당 날짜의 내역이 없습니다.");
            return;
        }

        List<Item> items = data.get(date);
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " : " + items.get(i).getPrice() + "원");
        }

        System.out.println("삭제할 내역 번호 입력 : ");
        int index = readInt() - 1; // 사용자에게 보여줄 때는 1부터 시작하지만, 내부적으로는 0부터 시작

        if (index < 0 || index >= items.size()) {
            System.out.println("유효하지 않은 번호입니다.");
            return;
        }

        Item removedItem = items.remove(index);
        System.out.println("[" + date + "] 내역에서 '" + removedItem.getName() + "'이 삭제되었습니다.");

        // 만약 해당 날짜의 내역이 비어있다면, 맵에서 제거
        if (items.isEmpty()) {
            data.remove(date);
            System.out.println("[" + date + "] 내역이 모두 삭제되어 기록에서 제거되었습니다.");
        }
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
