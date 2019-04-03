public class CardMissingException extends RuntimeException{
    public CardMissingException(int i){
        // To print card missing prompt
        super("Card Missing! card " + i + " is missing!");
    }
}
