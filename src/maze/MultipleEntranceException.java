package maze;

public class MultipleEntranceException extends InvalidMazeException {
    public MultipleEntranceException(String message, Throwable cause){
        super(message, cause);
    }
}