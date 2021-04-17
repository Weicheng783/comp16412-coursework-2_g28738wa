package maze.routing;

import java.util.Stack;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import maze.*;

public class RouteFinder{
    // <E> 
    private Maze maze;
    private Stack<Tile> route = new Stack<> ();
    private boolean finished = false;

    private List<Tile> blackList= new LinkedList<> ();

    public RouteFinder (Maze mazee) throws NoRouteFoundException {
        maze = mazee;
        // System.out.println("Successfully get maze!");
        // System.out.println(route.empty());
        // Tile testcase = maze.getEntrance();
        // System.out.println(testcase.toString());
        // route.push(testcase);
        // testcase = maze.getAdjacentTile(testcase,Maze.Direction.EAST);
        // route.push(testcase);
        // System.out.println(route.empty());
        // System.out.println(route.search(testcase));
        // System.out.println(testcase.toString());

        // Clock-wise finding...
        Tile entrance = maze.getEntrance();
        Tile temp = entrance;
        Tile next;
        route.push(entrance);
        while (isFinished() == false){
            next = maze.getAdjacentTile(temp,Maze.Direction.NORTH);
            if(next != null){
                if(next.toString() == "x"){
                    route.push(next);
                    finished = true;
                    temp = next;
                    break;
                }else if(next.toString() == "."){
                    if(blackList.contains(next) || route.search(next) != -1 ){
                        // continue;
                    }else{
                        route.push(next);
                        temp = next;
                        continue;
                    }

                }
            }


            next = maze.getAdjacentTile(temp,Maze.Direction.EAST);
            if(next != null){
                // System.out.println("east");
                if(next.toString() == "x"){
                    route.push(next);
                    finished = true;
                    temp = next;
                    break;
                }else if(next.toString() == "."){
                    if(blackList.contains(next) || route.search(next) != -1 ){
                        // continue;
                    }else{
                        route.push(next);
                        temp = next;
                        continue;
                    }

                }
            }

    
            next = maze.getAdjacentTile(temp,Maze.Direction.SOUTH);
            if(next != null){
                // System.out.println("south");
                if(next.toString() == "x"){
                    route.push(next);
                    finished = true;
                    temp = next;
                    break;
                }else if(next.toString() == "."){
                    if(blackList.contains(next) || route.search(next) != -1 ){
                        // continue;
                    }else{
                        route.push(next);
                        temp = next;
                        // System.out.println("GO HERE!");
                        continue;
                    }

                }
            }

        
            next = maze.getAdjacentTile(temp,Maze.Direction.WEST);
            if(next != null){
                if(next.toString() == "x"){
                    route.push(next);
                    finished = true;
                    temp = next;
                    break;
                }else if(next.toString() == "."){
                    if(blackList.contains(next) || route.search(next) != -1 ){
                        // continue;
                    }else{
                        route.push(next);
                        temp = next;
                        continue;
                    }

                }
            }



            // the very buttom option...
            if (route.size()==1){
                throw new NoRouteFoundException("No Route Found, please check your maze again.");
            }else{
                blackList.add(route.pop());
                temp = route.peek();
            }


        }

            System.out.println(route.size());
            System.out.println(route);
            System.out.println(blackList);        
        

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