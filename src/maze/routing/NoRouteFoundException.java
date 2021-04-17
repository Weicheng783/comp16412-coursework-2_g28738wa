package maze.routing;

public class NoRouteFoundException extends RuntimeException{
    public NoRouteFoundException(String msg){
        super(msg);
    }
}