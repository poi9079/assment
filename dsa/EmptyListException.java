public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        // To print empty list prompt
        super("List is empty.");
    }
}
