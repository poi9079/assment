package Acng;

public class resultPrinter {
    public static LinkedList modifyMessage(String text) {
        LinkedList message = new LinkedList();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                message.addToTail(Character.toUpperCase(c));
            }
        }
        return message;
    }

    public static LinkedList toNum(LinkedList message) {
        LinkedList code = new LinkedList();
        ListNode current;
        for (current = message.getHead(); current != null; current = current.next) {
            code.addToTail((char) current.data - 'A' );
        }
        return code;
    }

    public static LinkedList cryptNum(String enOrDe, LinkedList code, LinkedList key) {
        LinkedList num = new LinkedList();
        ListNode keycurrent = key.getHead();
        ListNode codecurrent;
        switch (enOrDe) {
            case "en": {
                for (codecurrent = code.getHead(); codecurrent != null; codecurrent = codecurrent.next) {
                    num.addToTail(((int) codecurrent.data + (int) keycurrent.data) % 26 +1);
                    keycurrent = keycurrent.next;
                }
                return num;
            }
            case "de": {
                for (codecurrent = code.getHead(); codecurrent != null; codecurrent = codecurrent.next) {
                    num.addToTail(((((int) codecurrent.data - (int) keycurrent.data) )+26) % 26 +1 );
                    keycurrent = keycurrent.next;
                }
                return num;
            }
        }
        return num;
    }

    public static void printEncryptResult(String enOrDe, LinkedList message, LinkedList code, LinkedList key, LinkedList num) {
        ListNode mscurrent = message.getHead();
        ListNode codecurrent = code.getHead();
        ListNode keycurrent = key.getHead();
        ListNode numcurrent = num.getHead();
        int letter;
        String resultmessage  = "";
        for (; codecurrent != null; codecurrent = codecurrent.next) {
            letter = (int) numcurrent.data;
            System.out.print(mscurrent.data + "\t" + ((int)codecurrent.data+1) + "\t" + keycurrent.data + "\t" + letter);
            System.out.println("\t" + (char) (letter + 64));
            mscurrent = mscurrent.next;
            keycurrent = keycurrent.next;
            numcurrent = numcurrent.next;
            resultmessage  += (char) (letter + 64);
        }
        if (enOrDe.equals("en"))
            System.out.println("Encrypted message: " + resultmessage );
        else
            System.out.println("Decrypted message: " + resultmessage );
    }


    public static void printKeystream(LinkedList key) {
        System.out.println("Keystream values: " + key);
    }
}