class MyCircularDeque {

    class Node{

        int val;
        Node prev;
        Node next;

        Node(int val){
            this.val = val;
        }
    }

    private Node start = null;
    private Node end = null;
    private int count;
    private int capacity;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.capacity = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(count == capacity){
            return false;
        }
        Node node = new Node(value);
        if(start == null){
            start = node;
            end = node;
        }else{
            node.next = start;
            start.prev = node;
            start = node;
        }
        count ++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(count == capacity){
            return false;
        }
        Node node = new Node(value);
        if(end == null){
            start = node;
            end = node;
        }else{
            node.prev = end;
            end.next = node;
            end = node;
        }
        count ++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(start == null){
            return false;
        }
        start = start.next;
        if(start == null){
            end = null;
        }else{
            start.prev = null;
        }
        count --;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(end == null){
            return false;
        }
        end = end.prev;
        if(end == null){
            start = null;
        }else{
            end.next = null;
        }
        count --;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return start == null ? -1 : start.val;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return end == null ? -1 : end.val;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */