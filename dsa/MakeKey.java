package Acng;

public class MakeKey {
    private LinkedList deck;
    private LinkedList pt1;
    private LinkedList pt2;
    private LinkedList pt3;
    private ListNode jokerADemo;
    private ListNode jokerBDemo;
    private int key;

    public MakeKey(LinkedList deck) {
        this.deck = deck;
        pt1 = new LinkedList();
        pt2 = new LinkedList();
        pt3 = new LinkedList();
        jokerADemo = deck.findJokerA();
        jokerBDemo = deck.findJokerB();
    }

    public LinkedList processAndPrint() {
        deck.Blackflap27(jokerADemo);
        System.out.println("S1: " + deck);
        deck.Blackflap28(jokerBDemo);
        System.out.println("S2: " + deck);
        firstShafu();
        System.out.println("S3: " + deck);
        secondShafu();
        System.out.println("S4: " + deck);
        Key();
        if (isskip())
            System.out.println("Joker: Key skipped");
        return pt3;
    }
    public LinkedList process() {
        deck.Blackflap27(jokerADemo);
        deck.Blackflap28(jokerBDemo);
        firstShafu();
        secondShafu();
        Key();
        return pt3;
    }
    private void cut() {
        ListNode current = deck.getHead();
        while (!isJoker(current)) { //build first part
            pt1.addToTail(current.data);
            current = current.next;
        }
        pt2.addToTail(current.data);    //add first joker
        current = current.next;
        while (!isJoker(current)) {
            pt2.addToTail(current.data);
            current = current.next;
        }
        pt2.addToTail(current.data);    //add next joker
        for (current = current.next; current != null; current = current.next)
            pt3.addToTail(current.data);
    }

    private void firstShafu() {
        cut();
        if (!isJoker(deck.getHead())) {
            pt2.addNodeToTail(pt1.getHead());//tail.next = head.head;
        } else
            pt1 = pt2;
        if (!isJoker(deck.getTail())) {
            pt3.addNodeToTail(pt2.getHead());//tail.next = middle.head;
        } else
            pt3 = pt2;
        pt3.setTail(pt1.getTail());//tail = head.tail;
        pt1 = new LinkedList();
        pt2 = new LinkedList();
        deck = pt3;
    }

    private void secondShafu() {
        ListNode current = deck.getHead();
        ListNode deckTail = deck.getTail();
        int num = (int) deck.getTail().data;
        if ((int) deck.getTail().data == 27 || (int) deck.getTail().data == 28)
            num = 27;
        for (int i = 0; i < num; i++) {
            pt1.addToTail(current.data);
            deck.removeFromHead();
            current = current.next;
        }
        current = pt1.getHead();
        deck.removeFromTail();
        while (current != null) {
            deck.addToTail(current.data);
            current = current.next;
        }
        deck.addToTail(deckTail.data);
    }


    public int Key() {
        ListNode current = deck.getHead();
        int top = (int) pt3.getHead().data;
        if (top == 27 || top == 28)
            top = 27;
        for (int i = 0; i < top; i++)
            current = current.next;
        key = (int) current.data;
        return key;
    }

    public boolean isskip() {
        if (key == 27 || key == 28) {
            return true;
        }return false;
    }

    public int getKey() {
        return key;
    }

    public boolean isJoker(ListNode node) {
        return ((int) node.data == 27 || (int) node.data == 28);
    }
}