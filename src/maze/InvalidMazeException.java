package maze;

/**
 * InvalidMazeException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class InvalidMazeException extends Exception{
    /**
    * The Constructor of InvalidMazeException. (public)
    * @param msg: The exception message conveyed to the user.
    */
    public InvalidMazeException(String msg){
        super(msg);
    }

}