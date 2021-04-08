package maze;

public class NoExitException extends InvalidMazeException {
    public NoExitException(String message, Throwable cause){
        super(message, cause);
    }
}