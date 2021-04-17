import maze.*;
import maze.routing.*;
import java.io.IOException;

public class MazeDriver  {
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