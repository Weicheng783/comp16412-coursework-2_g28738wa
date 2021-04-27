package maze;

/**
 * NoExitException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class NoExitException extends InvalidMazeException {
    /**
    * The Constructor of NoExitException. (public)
    * @param message: The exception message conveyed to the user.
    */
    public NoExitException(String message){
        super(message);
    }
}