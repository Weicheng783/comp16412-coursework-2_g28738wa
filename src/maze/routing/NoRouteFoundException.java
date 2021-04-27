package maze.routing;

/**
 * NoRouteFoundException class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class NoRouteFoundException extends Exception{

    /**
     * The Constructor of NoRouteFoundException. (public)
     * @param msg: The exception message conveyed to the user.
     */
    public NoRouteFoundException(String msg){
        super(msg);
    }
}