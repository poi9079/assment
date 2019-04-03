package Acng;

public class WrongUsageException extends RuntimeException {
    public WrongUsageException() {
        // To print wrong usage prompt
        super("Wrong usage! It should be like this : java SolitaireEncryption [option] [deck_file] [message_string]\noption :\n\ten  \tencrypting the message\n\tde  \tdecrypting the message\n\tkeygen\tshowing the keystream values");
    }
}
