package maze.routing;

import java.util.Stack;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import maze.*;

public class RouteFinder implements Serializable{
    // <E> 
    private Maze maze;
    private Stack<Tile> route = new Stack<> ();
    private boolean finished = false;

    private boolean firstenter = true;

    private Tile entrance;
    private Tile temp;
    private Tile next;

    private List<Tile> blackList= new LinkedList<> ();

    // public void RouteFinder(){};//!!!notice this!
    // public RouteFinder obj;

    public RouteFinder (Maze mazee) {
        //!!!void?!!!
        this.maze = mazee;
        entrance = this.maze.getEntrance();
        temp = entrance;
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

    }

    public Maze getMaze(){
        return maze;
    }

    public List<Tile> getRoute(){
        return route;
    }

    public List<Tile> getBlackList(){
        return blackList;
    }

    public boolean isFinished(){
        if(finished == true){
            return true;
        }else{
            return false;
        }
    }

    public static RouteFinder load(String str) {
        try (
            ObjectInputStream objectStream = new ObjectInputStream(
                new FileInputStream( str )
            );
        ) {
            System.out.println("Loading Successful!");
            return (RouteFinder)objectStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not read " + str );
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: problem when reading "+ str );
            return null;
        }

    }

    // public void savepath(String str){
    //     save(str);
    // }

    public void save(String str) throws IOException{
        ObjectOutputStream objectStream = null;
        try {
            objectStream = new ObjectOutputStream(new FileOutputStream( str  ));
            // System.out.println(this.isFinished());
            objectStream.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not open " + str +" for writing.");
        } catch (IOException e) {
            System.out.println("Error: IOException when writing " + str );
            throw new IOException("Ooops, some thing went wrong.", e);
        } finally {
            try {
                objectStream.close();
            } catch (IOException e) {
                System.out.println("Error: IOException when closing " + str );
            }
        }
    }

    public boolean step() throws NoRouteFoundException{
        // Clock-wise finding...
        if (firstenter == true){

            this.route.push(entrance);
            firstenter = false;
            return false;
        }

        if (isFinished() == false){
            next = this.maze.getAdjacentTile(temp,Maze.Direction.NORTH);
            if(next != null){
                if(next.toString() == "x"){
                    this.route.push(next);
                    this.finished = true;
                    temp = next;
                    return true;
                }else if(next.toString() == "."){
                    if(this.blackList.contains(next) || this.route.search(next) != -1 ){
                        // continue;
                    }else{
                        this.route.push(next);
                        temp = next;
                        return false;
                    }

                }
            }


            next = this.maze.getAdjacentTile(temp,Maze.Direction.EAST);
            if(next != null){
                // System.out.println("east");
                if(next.toString() == "x"){
                    this.route.push(next);
                    this.finished = true;
                    temp = next;
                    return true;
                }else if(next.toString() == "."){
                    if(this.blackList.contains(next) || this.route.search(next) != -1 ){
                        // continue;
                    }else{
                        this.route.push(next);
                        temp = next;
                        return false;
                    }

                }
            }

    
            next = this.maze.getAdjacentTile(temp,Maze.Direction.SOUTH);
            if(next != null){
                // System.out.println("south");
                if(next.toString() == "x"){
                    this.route.push(next);
                    this.finished = true;
                    temp = next;
                    return true;
                }else if(next.toString() == "."){
                    if(this.blackList.contains(next) || this.route.search(next) != -1 ){
                        // continue;
                    }else{
                        this.route.push(next);
                        temp = next;
                        // System.out.println("GO HERE!");
                        return false;
                    }

                }
            }

        
            next = this.maze.getAdjacentTile(temp,Maze.Direction.WEST);
            if(next != null){
                if(next.toString() == "x"){
                    this.route.push(next);
                    this.finished = true;
                    temp = next;
                    return true;
                }else if(next.toString() == "."){
                    if(this.blackList.contains(next) || this.route.search(next) != -1 ){
                        // continue;
                    }else{
                        this.route.push(next);
                        temp = next;
                        return false;
                    }

                }
            }



            // the very buttom option...
            if (this.route.size()==1){
                throw new NoRouteFoundException("No Route Found, please check your maze again.");
            }else{
                this.blackList.add(this.route.pop());
                temp = this.route.peek();

            }
            
            return false;
            
        }else{
            System.out.println("The maze is solved now, no more step.");
            return true;
        }

        // System.out.println(this.route.size());
        // System.out.println(this.route);
        // System.out.println(this.blackList);
    }

    public String toString(){
        // String a = new String();

        String all = "";
        for (int i = this.maze.lineno-1; i >= 0; i--){
            String split = "";
            for (int ii = 0; ii < this.maze.colno; ii ++){
                
                // this.blackList.contains(next) || this.route.search(next) != -1
                if (   this.blackList.contains(  this.maze.getTileAtLocation(this.maze.setCoord(i,ii))  )   ){
                    split = split + "-";
                }else if (  this.route.search(  this.maze.getTileAtLocation(this.maze.setCoord(i,ii))  ) != -1  ){
                    split = split + "*";
                }else {
                    split = split + this.maze.getTileAtLocation(this.maze.setCoord(i,ii)).toString();
                }

            }
            if (i != 0){
                split = split + "\n";
            }
            all = all + split;
        }
        return all;

    }

}