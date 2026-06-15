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
                Node prev = n.prev;
                if (prev == null) {
                    buckets[index] = n.next;
                } else {
                    prev.next = n.next;
                }
            }
        }

        size--;
    }
}
