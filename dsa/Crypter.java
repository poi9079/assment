package Acng;

public class Crypter {
    public static LinkedList modifyMessage(String text) {
        // Take the string reformat the message case and output as linkedlist
        LinkedList message = new LinkedList();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                message.addToTail(Character.toUpperCase(c));
            }
        }
        return message;
    }

    public static LinkedList toNum(LinkedList message) {
        //Reformat the message to number i call it code
        LinkedList code = new LinkedList();
        ListNode current;
        for (current = message.getHead(); current != null; current = current.next) {
            code.addToTail((char) current.data - 'A' );
        }
        return code;
    }

    public static LinkedList cryptNum(String enOrDe, LinkedList code, LinkedList key) {
        //To encrypt or decrypt the code output the final password number I call it num
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

    public static void printCryptResult(String enOrDe, LinkedList message, LinkedList code, LinkedList key, LinkedList num) {
        //To print the crypt result
        ListNode mscurrent = message.getHead();
        ListNode codecurrent = code.getHead();
        ListNode keycurrent = key.getHead();
        ListNode numcurrent = num.getHead();
        int letter;
        String resultmessage  = "";
        for (; codecurrent != null; codecurrent = codecurrent.next) {
            letter = (int) numcurrent.data;
            System.out.print(" " + mscurrent.data + "\t" + ((int)codecurrent.data+1) + "\t" + keycurrent.data + "\t" + letter);
            System.out.println("\t" + (char) (letter + 64));
            mscurrent = mscurrent.next;
            keycurrent = keycurrent.next;
            numcurrent = numcurrent.next;
            resultmessage  += (char) (letter + 64);
        }
        if (enOrDe.equals("en"))
            System.out.println("\n Encrypted message: " + resultmessage );
        else
            System.out.println("\n Decrypted message: " + resultmessage );
    }


    public static void printKeystream(LinkedList key) {
        //To print keystream
        System.out.println("\n Keystream values: " + key);
    }
}