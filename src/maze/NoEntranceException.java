package maze;

/**
 * NoEntranceException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class NoEntranceException extends InvalidMazeException {
    /**
    * The Constructor of NoEntranceException. (public)
    * @param message: The exception message conveyed to the user.
    */
    public NoEntranceException(String message){
        super(message);
    }
}