public class CardNotEnoughException extends RuntimeException{
    public CardNotEnoughException(){
        // To print card not enough prompt
        super("Card Not Enough! Add more to 28!");
    }
}
