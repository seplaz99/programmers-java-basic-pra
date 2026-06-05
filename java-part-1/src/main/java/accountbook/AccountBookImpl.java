package main.java.accountbook;

import java.util.*;

public class AccountBookImpl implements AccountBook {
    private Map<String, List<Item>> data = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public boolean isEmptyOrPrintDates() {
        if(data.isEmpty()) {
            System.out.println("기록이 없습니다.");
            return true;
        }

        List<String> dates = new ArrayList<>(data.keySet());
        Collections.sort(dates, Collections.reverseOrder());
        for (String d : dates) System.out.println(d);

        return false;
    }

    public void addAccount() {
        System.out.println("날짜 입력 (예: 2024-09-04)");
        String date = sc.nextLine();

        List<Item> itemList;
        if (data.containsKey(date)) {
            itemList = data.get(date);
        } else {
            itemList = new ArrayList<>();
        }

        while(true) {
            System.out.println("항목 이름");
            String name = sc.nextLine();

            System.out.println("금액");
            int price = Integer.parseInt(sc.nextLine());

            Item item = new Item(name, price);
            itemList.add(item);

            System.out.println("더 추가할까요? (y/n)");
            String addMore = sc.nextLine();
            if(addMore.equals("n")) {
                break;
            }
        }

        data.put(date, itemList);

        System.out.println("\n[" + date + "] 등록 완료");
        int totalPrice = 0;

        for (Item item : itemList) {
            System.out.println(item.getName() + " : " + item.getPrice() + "원");
            totalPrice += item.getPrice();
        }

        System.out.println("합계 : " + totalPrice + "원");
    }

    public void showAccounts() {
        if(isEmptyOrPrintDates()) return;

        System.out.println("조회할 날짜 입력 : ");
        String date = sc.nextLine();

        if(!data.containsKey(date)) {
            System.out.println("날짜가 없습니다.");
        } else {
            System.out.println("[" + date + "]");
            int totalPrice = 0;
            for (Item item : data.get(date)) {
                System.out.println(item.getName() + " : " + item.getPrice());
                totalPrice += item.getPrice();
            }
            System.out.println("합계 : " + totalPrice + "원");
        }
    }

    public void removeAll() {
        if(isEmptyOrPrintDates()) return;

        System.out.println("삭제할 날짜 입력 : ");
        String date = sc.nextLine();

        if(!data.containsKey(date)) {
            System.out.println("날짜가 없습니다.");
        } else {
            data.remove(date);
            System.out.println("항목을 모두 삭제하였습니다.");
        }
    }

    public void removeAccount() {
        if(isEmptyOrPrintDates()) return;

        System.out.println("삭제할 항목의 날짜 입력 : ");
        String date = sc.nextLine();

        if(!data.containsKey(date)) {
            System.out.println("날짜가 없습니다.");
        } else {
            List<Item> itemList = data.get(date);

            for (int i = 0; i < itemList.size(); i++) {
                Item item = itemList.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " : " + item.getPrice());
            }

            System.out.println("삭제할 항목의 번호를 입력하세요.");
            int deleteNum = sc.nextInt();
            sc.nextLine();

            if(deleteNum < 1 || deleteNum > data.get(date).size()) {
                System.out.println("잘못된 번호를 입력하셨습니다.");
                return;
            }

            itemList.remove(deleteNum - 1);

            if(itemList.isEmpty()) {
                data.remove(date);
                System.out.println(date + "의 모든 항목이 삭제되어 날짜가 제거되었습니다.");
            }

            System.out.println("항목을 삭제하였습니다.");

        }
    }
}
