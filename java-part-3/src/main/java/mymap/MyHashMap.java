package mymap;

public class MyHashMap {
    static class Node {
        String key;
        int value;
        Node next;
        Node prev;
        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets;
    private int capacity = 16;
    private int size = 0;

    public MyHashMap() {
        buckets = new Node[capacity];
    }

    int getIndex(String key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        if (hash < 0) {
            hash = -hash;
        }
        int index = hash % capacity;

        return index;
    }

    public void put(String key, int value) {
        int index = getIndex(key);
        Node head = buckets[index];

        for (Node n = head; n != null; n = n.next) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        Node node = new Node(key, value);
        node.next = head;

        if (head != null) {
            head.prev = node;
        }

        buckets[index] = node;
        size++;
    }

    public Integer get(String key) {
        int index = getIndex(key);
        Node head = buckets[index];
        for (Node n = head; n != null; n = n.next) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public void remove(String key) {
        int index = getIndex(key);
        Node head = buckets[index];

        for (Node n = head; n != null; n = n.next) {
            if (n.key.equals(key)) {
                if (n.prev == null) {
                    buckets[index] = n.next;
                } else {
                    n.prev.next = n.next;
                }

                if (n.next != null) {
                    n.next.prev = n.prev;
                }

                size--;
                return;
            }
        }
    }
}
