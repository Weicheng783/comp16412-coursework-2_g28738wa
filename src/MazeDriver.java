import maze.*;
import maze.routing.*;
import java.io.IOException;

/**
 * MazeDriver class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class MazeDriver  {
    /**
     * The main method of the MazeDriver. (public)
     */
    public static void main(String args[]) throws InvalidMazeException, IOException {
        Maze test = Maze.fromTxt("/home/csimage/GitRepos/comp16412-coursework-2_g28738wa/resources/mazes/maze1.txt");
        System.out.println(test.toString());
        // test.setMaze();
        RouteFinder bbb = new RouteFinder(test);
        System.out.println(bbb.isFinished());
        bbb.save("savedmazetest");
        RouteFinder ccc = bbb.load("savedmazetest");
        System.out.println( ccc.toString() );


    }
}