package maze;

/**
 * RaggedMazeException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class RaggedMazeException extends InvalidMazeException {
    /**
    * The Constructor of RaggedMazeException. (public)
    * @param message: The exception message conveyed to the user.
    */
    public RaggedMazeException(String message){
        super(message);
    }
}