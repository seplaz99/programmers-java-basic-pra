package mylist;

class MyLinkedList {
    // [Step 1] 노드 한 칸: 데이터 + 앞/뒤 노드의 주소
    static class Node {
        String data;
        Node prev;   // 앞 노드
        Node next;   // 뒤 노드

        Node(String data) {
            this.data = data;
        }
    }

    // [Step 2] 필드 (작성돼 있음)
    private Node head;   // 첫 노드
    private Node tail;   // 마지막 노드
    private int size;

    // [Step 3] 맨 뒤에 추가
    void addLast(String data) {
        Node node = new Node(data);

        if (head == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // [Step 4] 연결 상태 출력 (제공됨 — 읽고 이해만 하세요)
    void printLinks() {
        Node cur = head;
        while (cur != null) {
            String p = (cur.prev == null) ? "null" : cur.prev.data;
            String n = (cur.next == null) ? "null" : cur.next.data;
            System.out.print("[" + p + " <- " + cur.data + " -> " + n + "] ");
            cur = cur.next;
        }
        System.out.println();
    }

    // [Step 5] 맨 앞에 추가
    void addFirst(String data) {
        Node node = new Node(data);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    // [Step 6] index번째 노드 찾기
    private Node nodeAt(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    String get(int index) {
        return nodeAt(index).data;
    }

    // [Step 7] index 위치에 삽입 (양옆 연결만 바꾸기)  ★핵심★
    void insert(int index, String data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node next = nodeAt(index);
            Node prev = next.prev;
            Node node = new Node(data);
            node.prev = prev;
            node.next = next;
            prev.next = node;
            next.prev = node;
        }
        size++;
    }

    // [도전] index 위치 노드 삭제
    void remove(int index) {
        // TODO (도전): 삭제할 노드의 prev 와 next 를 서로 연결하고 size--
        //              (맨 앞/맨 뒤 삭제 시 head/tail 갱신 주의)
        if (size == 0 || index < 0 || index >= size) {
            System.out.println("삭제할 노드가 없습니다.");
            return;
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node target = nodeAt(index);
            target.prev.next = target.next;
            target.next.prev = target.prev;
        }

        size--;
    }

    int size() {
        return size;
    }
}

public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        // --- Step 3 + 4 확인 ---
        list.addLast("가");
        list.addLast("나");
        list.addLast("다");
        System.out.print("addLast 후: ");
        list.printLinks();
        // 기대: [null <- 가 -> 나] [가 <- 나 -> 다] [나 <- 다 -> null]

        // --- Step 5 확인 ---
        list.addFirst("앞");
        System.out.print("addFirst 후: ");
        list.printLinks();
        // 기대: [null <- 앞 -> 가] [앞 <- 가 -> 나] [가 <- 나 -> 다] [나 <- 다 -> null]

        // --- Step 6 확인 ---
        System.out.println("get(2) = " + list.get(2));   // 기대: 나

        // --- Step 7 확인 ---
        list.insert(2, "끼움");
        System.out.print("insert 후: ");
        list.printLinks();
        // 기대: [null <- 앞 -> 가] [앞 <- 가 -> 끼움] [가 <- 끼움 -> 나] [끼움 <- 나 -> 다] [나 <- 다 -> null]

        // --- 도전 ---
        list.remove(2);
        System.out.print("remove 후: ");
        list.printLinks();
        // 기대: [null <- 앞 -> 가] [앞 <- 가 -> 나] [가 <- 나 -> 다] [나 <- 다 -> null]
    }
}
