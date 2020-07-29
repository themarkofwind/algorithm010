//Design and implement a data structure for Least Recently Used (LRU) cache. It 
//should support the following operations: get and put. 
//
// get(key) - Get the value (will always be positive) of the key if the key exis
//ts in the cache, otherwise return -1. 
//put(key, value) - Set or insert the value if the key is not already present. W
//hen the cache reached its capacity, it should invalidate the least recently used
// item before inserting a new item. 
//
// The cache is initialized with a positive capacity. 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// Example: 
//
// 
//LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
// 
//
// 
// Related Topics Design 
// 👍 6076 👎 261


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class Node {
        Node prev;
        Node next;
        int key;
        int val;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }

    class LinkedList {
        Node head;
        Node tail;
        public LinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {
            if (null == node) return;
            node.next = head.next;
            head.next = node;
            node.prev = head;
            node.next.prev = node;
        }

        public void remove(Node node) {
            if (null == node) return;
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        public Node removeLast() {
            // empty
            if (tail.prev == head) return null;
            Node toDelete = tail.prev;
            remove(toDelete);
            return toDelete;
        }
    }

    LinkedList list;
    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.list = new LinkedList();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = getNode(key);
        return null == node ? -1 : node.val;
    }

    private Node getNode(int key) {
        Node node = map.get(key);
        if (null == node) return null;
        // 非头结点
        if (node.prev != list.head) {
            list.remove(node);
            list.addFirst(node);
        }
        return node;
    }
    
    public void put(int key, int value) {
        // 结点存在，直接更新
        Node node = getNode(key);
        if (null != node) {
            node.val = value;
            return;
        }
        // 不存在，需要新增
        Node toAdd = new Node(key, value);
        if (capacity > 0) {
            capacity--;
        } else {
            // full
            Node toDel = list.removeLast();
            map.remove(toDel.key);
        }
        list.addFirst(toAdd);
        map.put(key, toAdd);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
