package Acng;

public class DeckFormatException extends RuntimeException{
    public DeckFormatException(){
        // To print deck format wong prompt
        super("Deck Format incorrect!We just accept number between 1-28!");
    }
}
