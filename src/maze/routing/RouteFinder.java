package maze.routing;

import java.util.Stack;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import maze.*;

public class RouteFinder {
    private Maze maze;
    private Stack<Tile> route;
    private boolean finished;

    public RouteFinder (Maze mazee){
        maze = mazee;
        System.out.println("Successfully get maze!");
        Tile testcase = maze.getEntrance();
        System.out.println(testcase.toString());
        testcase = maze.getAdjacentTile(testcase,Maze.Direction.EAST);
        System.out.println(testcase.toString());

    }

    public Maze getMaze(){
        return maze;
    }

    public List<Tile> getRoute(){
        return route;
    }

    public boolean isFinished(){
        if(finished == true){
            return true;
        }else{
            return false;
        }
    }

    public static RouteFinder load(String str){
        return null;
    }

    public void save(String str){

    }

    public boolean step(){
        return false;
    }

    public String toString(){
        return "";
    }

}