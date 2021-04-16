import maze.*;
import maze.routing.*;

public class MazeDriver  {
    public static void main(String args[]) throws InvalidMazeException {
        Maze test = Maze.fromTxt("/home/csimage/GitRepos/comp16412-coursework-2_g28738wa/resources/mazes/maze1.txt");
        System.out.println(test.toString());
        // test.setMaze();
        RouteFinder a = new RouteFinder(test);
        System.out.println(a.isFinished());


    }
}