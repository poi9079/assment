public class MakeKey {
    private LinkedList deck;//the deck
    private LinkedList pt1;//save point 1 for cutting the deck part one
    private LinkedList pt2;//save point 2 for cutting the deck part two
    private LinkedList pt3;//save point 3 for cutting the deck part three
    private int key;//The keystream value

    public MakeKey(LinkedList deck) {
        //initialize
        this.deck = deck;
        pt1 = new LinkedList();
        pt2 = new LinkedList();
        pt3 = new LinkedList();

    }

    public LinkedList processAndPrint() {
        //Showing the steps
        deck.Blackflap27(deck.findJokerA());
        System.out.println("S1: " + deck);
        deck.Blackflap28(deck.findJokerB());
        System.out.println("S2: " + deck);
        tripleCut();
        System.out.println("S3: " + deck);
        countCut();
        System.out.println("S4: " + deck);
        Key();
        if (isskip())
            System.out.println("Joker: Key skipped");
        return pt3;
    }
    public LinkedList process() {
        //Just do the process at background
        deck.Blackflap27(deck.findJokerA());
        deck.Blackflap28(deck.findJokerB());
        tripleCut();
        countCut();
        Key();
        return pt3;
    }
    private void cut() {
        //Cut the deck to three part
        ListNode current = deck.getHead();
        while (!isJoker(current)) {//cut first part
            pt1.addToTail(current.data);
            current = current.next;
        }
        pt2.addToTail(current.data);//add first joker
        current = current.next;
        while (!isJoker(current)) {//cut the second part
            pt2.addToTail(current.data);
            current = current.next;
        }
        pt2.addToTail(current.data);//add next joker
        for (current = current.next; current != null; current = current.next)//the remaining part to part three
            pt3.addToTail(current.data);
    }

    private void tripleCut() {
        //Perform a triple cut
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

    private void countCut() {
        //Perform a count cut
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
        //find the key stream value
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
        //Check  (N+1)-th card is a joker
        return key == 27 || key == 28;
    }

    public int getKey() {
        //provide the key
        return key;
    }

    public boolean isJoker(ListNode node) {
        //check the card is a joker
        return ((int) node.data == 27 || (int) node.data == 28);
    }
}