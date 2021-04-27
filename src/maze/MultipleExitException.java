package maze;


/**
 * MultipleExitException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class MultipleExitException extends InvalidMazeException {
    /**
    * The Constructor of MultipleExitException. (public)
    * @param message: The exception message conveyed to the user.
    */
    public MultipleExitException(String message){
        super(message);
    }
}