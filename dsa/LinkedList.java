public class LinkedList {
    private ListNode head;
    private ListNode tail;
    private int count;

    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public int getCount() {
        return count;
    }

    public ListNode findJokerA() {
        ListNode jokerADemo = new ListNode(27);
        ListNode current = head;
        while (current.data != jokerADemo.data)
            current = current.next;
        return current;
    }

    public ListNode findJokerB() {
        ListNode jokerADemo = new ListNode(28);
        ListNode current = head;
        while (current.data != jokerADemo.data)
            current = current.next;
        return current;
    }

    public void Blackflap27(ListNode jokerA) {
        if (jokerA.next == null) {
            jokerA.data = head.data;
            head.data = 27;
        } else {
            jokerA.data = jokerA.next.data;
            jokerA.next.data = 27;
        }
    }

    public void Blackflap28(ListNode jokerB) {
        if (jokerB.next == null) {
            jokerB.data = head.data;
            head.data = head.next.data;
            head.next.data = 28;
        } else if (jokerB.next.next == null) {
            jokerB.data = jokerB.next.data;
            jokerB.next.data = head.data;
            head.data = 28;
        } else {
            jokerB.data = jokerB.next.data;
            jokerB.next.data = jokerB.next.next.data;
            jokerB.next.next.data = 28;
        }

    }


    public void addToHead(Object item) {
        count++;
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            head = new ListNode(item, head);
        }
    }

    public void addToTail(Object item) {
        count++;
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            tail.next = new ListNode(item);
            tail = tail.next;
        }
    }

    public Object removeFromHead() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        count--;
        Object item = head.data;
        if (head == tail) // there's only one single node
            head = tail = null;
        else
            head = head.next;
        return item;
    }

    public Object removeFromTail() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        count--;
        Object item = tail.data;
        if (head == tail) {   // there is only one node
            head = tail = null;
            return item;
        }
        // search for the second last node
        ListNode current = head;
        while (current.next != tail)
            current = current.next;
        // set second last node as new tail
        tail = current;
        tail.next = null;
        return item;
    }

    public String toString() {
        String s = "[ ";

        // traverse the list from head towards tail
        ListNode current = head;
        while (current != null) {
            s += current.data + " ";
            current = current.next;
        }
        return s + "]";
    }

    public ListNode getHead() {
        return head;
    }

    public ListNode getTail() {
        return tail;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public void setTail(ListNode tail) {
        this.tail = tail;
    }

    public void addNodeToTail(ListNode node) {
        count++;
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
    }
}





