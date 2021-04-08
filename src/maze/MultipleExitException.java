package maze;

public class MultipleExitException extends InvalidMazeException {
    public MultipleExitException(String message, Throwable cause){
        super(message, cause);
    }
}