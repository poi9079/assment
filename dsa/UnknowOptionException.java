public class UnknowOptionException extends RuntimeException {
    public UnknowOptionException(){
        super(" Wrong option!\n Option :\n\ten  \tencrypting the message\n\tde  \tdecrypting the message\n\tkeygen\tshowing the keystream values");
    }
}
