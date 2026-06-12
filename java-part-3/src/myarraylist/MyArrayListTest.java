package myarraylist;

import java.util.Arrays;

class MyArrayList {
    // [Step 1] 필드 (작성돼 있음): 데이터를 담을 배열과 현재 개수
    private String[] arr = new String[10];
    private int size = 0;

    // [Step 2] 맨 뒤에 추가
    void addLast(String data) {
        arr[size++] = data;
    }

    // [Step 3] 인덱스로 꺼내기
    String get(int index) {
        return arr[index];
    }

    // [Step 4] 현재 개수
    int size() {
        return size;
    }

    // [Step 5] 공간이 꽉 찼으면 배열을 2배로 늘리기
    private void ensureCapacity() {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    // [Step 6] 맨 앞에 추가  ★핵심★
    void addFirst(String data) {
        ensureCapacity();

        for (int i = size; i > 0; i--) {
            arr[i] = arr[i - 1];
        }

        arr[0] = data;
        size++;
    }

    // [도전] index 위치에 삽입
    void insert(int index, String data) {
        ensureCapacity();

        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }

        arr[index] = data;
        size++;
    }

    // [도전] index 위치 삭제
    void remove(int index) {
        if (size == 0) {
            System.out.println("비어있습니다.");
            return;
        }

        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }

        arr[size - 1] = null;
        size--;
    }
}

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();

        // --- Step 2~4 확인 ---
        list.addLast("가");
        list.addLast("나");
        list.addLast("다");
        System.out.println("size = " + list.size());                 // 기대: 3
        System.out.println("0,1,2 = " + list.get(0) + ", "
                + list.get(1) + ", "
                + list.get(2));                // 기대: 가, 나, 다

        // --- Step 6 확인 ---
        list.addFirst("앞");
        System.out.println("addFirst 후 0,1 = " + list.get(0) + ", " + list.get(1)); // 기대: 앞, 가
        System.out.println("size = " + list.size());                 // 기대: 4

        // --- 도전 ---
        list.insert(2, "가나");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        list.remove(2);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
