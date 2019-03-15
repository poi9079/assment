package Acng;

import java.io.*;
import java.util.*;

import static Acng.resultPrinter.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String text = "", option = null;
        LinkedList key = new LinkedList(), deck = new LinkedList(), message, code, num;
        try {
            option = args[0];
            deck = readDeckFile(args[1]);
            text = args[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("java SolitaireEncryption [option] [deck_file] [message_string]");
        }
        message = modifyMessage(text);
        code = toNum(message);
        switch (option) {
            case "keygen":
                keyGen(1, message, deck, key);
                printKeystream(key);
                break;
            case "en":
                keyGen(0, message, deck, key);
                num = cryptNum("en", code, key);
                printEncryptResult("en", message, code, key, num);
                break;
            case "de":
                keyGen(0, message, deck, key);
                num = cryptNum("de", code, key);
                printEncryptResult("de", message, code, key, num);
                break;
        }
    }

    private static LinkedList readDeckFile(String deckFilePath) throws FileNotFoundException {
        LinkedList deck = new LinkedList();
        Scanner file = new Scanner(new File(deckFilePath));
        while (file.hasNextInt()) {
            deck.addToTail(file.nextInt());
        }
        return deck;
    }

    private static void keyGen(int printOrNot, LinkedList message, LinkedList deck, LinkedList key) {
        MakeKey mk;
        if (printOrNot == 1) {
            for (int i = 0; i <= message.getCount(); i++) {
                do {
                    mk = new MakeKey(deck);
                    deck = mk.processAndPrint();
                } while (mk.isskip());
                key.addToTail(mk.Key());
                System.out.println("Key " + i + ": " + mk.getKey());
            }
        } else
            for (int i = 0; i < message.getCount(); i++) {
                do {
                    mk = new MakeKey(deck);
                    deck = mk.process();
                } while (mk.isskip());
                key.addToTail(mk.Key());
            }
    }
}
