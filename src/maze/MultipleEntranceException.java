package maze;

/**
 * MultipleEntranceException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class MultipleEntranceException extends InvalidMazeException {

    /**
    * The Constructor of MultipleEntranceException. (public)
    * @param message: The exception message conveyed to the user.
    */
    public MultipleEntranceException(String message){
        super(message);
    }
}