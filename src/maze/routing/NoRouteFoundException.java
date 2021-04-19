package maze.routing;

public class NoRouteFoundException extends Exception{
    public NoRouteFoundException(String msg){
        super(msg);
    }
}