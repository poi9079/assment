import javax.swing.text.html.Option;
import java.io.*;
import java.util.*;

public class SolitaireEncryption {
    public static void main(String[] args) throws FileNotFoundException {
        String text = "", option = null;
        Crypter crypter = new Crypter();
        LinkedList key = new LinkedList(), deck = new LinkedList(), message, code, num;
        try {
            checkUsage(args);
            option = args[0];
            deck = readDeck(args[1]);
            text = args[2];
            message = crypter.modifyMessage(text);
            code = crypter.toNum(message);
            switch (option) {//decide what function need to process
                case "keystream":
                    keyGen(1, message, deck, key);
                    crypter.printKeystream(key);
                    break;
                case "keygen":
                    keyGen(1, message, deck, key);
                    crypter.printKeystream(key);
                    break;
                case "en":
                    keyGen(0, message, deck, key);
                    num = crypter.cryptNum("en", code, key);
                    crypter.printCryptResult("en", message, code, key, num);
                    break;
                case "de":
                    keyGen(0, message, deck, key);
                    num = crypter.cryptNum("de", code, key);
                    crypter.printCryptResult("de", message, code, key, num);
                    break;
                default:
                    throw new UnknowOptionException();
            }
        } catch (FileNotFoundException | CardMissingException | CardNotEnoughException | CardTooMuchException | CardDuplicateException | DeckFormatException | WrongUsageException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static LinkedList readDeck(String deckPath) throws FileNotFoundException {
        //insert the deck to linkedlst
        checkDeck(deckPath);
        LinkedList deck = new LinkedList();
        Scanner file = new Scanner(new File(deckPath));
        while (file.hasNextInt()) {
            deck.addToTail(file.nextInt());
        }
        return deck;
    }

    private static void keyGen(int printOrNot, LinkedList message, LinkedList deck, LinkedList key) {
        //Integrate the keygen process and decide print or not
        MakeKey mk;
        if (printOrNot == 1) {
            for (int i = 1; i <= message.getCount(); i++) {
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

    private static void checkDeck(String deckFilePath) throws FileNotFoundException {
        //validate card integrity
        int[] deck = new int[28];
        int cardNum = 0;

        Scanner file = new Scanner(new File(deckFilePath));
        while (file.hasNextInt()) {

            deck[cardNum] = file.nextInt();

            if (deck[cardNum] > 28 || deck[cardNum] < 1)
                throw new DeckFormatException();

            cardNum++;
        }
        if (cardNum > 28)
            throw new CardTooMuchException();


        if (cardNum < 28)
            throw new CardNotEnoughException();

        Arrays.sort(deck);

        for (int i = 1; i <= 28; i++) {
            if (deck[i - 1] != (i)) {
                if (deck[i - 1] > i)
                    throw new CardMissingException(i);
                else if (deck[i - 1] < i)
                    throw new CardDuplicateException();
            }
        }
    }

    private static void checkUsage(String[] args) {
        //validate usage
        if (args.length < 3 || args.length > 3)
            throw new WrongUsageException();
    }
}

