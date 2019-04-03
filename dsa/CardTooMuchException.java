package Acng;

public class CardTooMuchException extends RuntimeException{
    public CardTooMuchException(){
        // To print card is over 28 prompt
        super("Card Too Much! Reduce it to 28!");
    }
}
