package Acng;

public class CardDuplicateException extends RuntimeException {
    public CardDuplicateException(){
        // To print card duplicate exception prompt
        super("Card Duplicate! Find it and delete it!");
    }
}