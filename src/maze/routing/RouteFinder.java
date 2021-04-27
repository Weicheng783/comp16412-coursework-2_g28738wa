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


/**
 * RouteFinder class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class RouteFinder implements Serializable{

    private Maze maze;
    private Stack<Tile> route = new Stack<> ();
    private boolean finished = false;

    private boolean firstenter = true;

    private Tile entrance;
    private Tile temp;
    private Tile next;

    private List<Tile> blackList= new LinkedList<> ();

    /**
     * RouteFinder constructor. (public)
     * @param mazee: A type Maze object required as input.
     */
    public RouteFinder (Maze mazee) {
        this.maze = mazee;
        entrance = this.maze.getEntrance();
        temp = entrance;

    }

    /**
     * Get stored Maze. (public)
     * @return The stored Maze object.
     */
    public Maze getMaze(){
        return maze;
    }

    /**
     * Get stored Route. (public)
     * @return An linkedlist of Tiles from the start to the last stack of tile entered.
     */
    public List<Tile> getRoute(){
        return route;
    }

    /**
     * Get BlackListed tiles(if a dead route encountered, then the tiles are enter blacklist). (public)
     * @return An list of BlackListed Tiles for use.
     */
    public List<Tile> getBlackList(){
        return blackList;
    }

    /**
     * Get a Boolean value if the Route is already completed. (public)
     * @return A boolean value if the Route is already finished.
     */
    public boolean isFinished(){
        if(finished == true){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Load a RouteFinder object. (public)
     * @param str: The file path to load a RouteFinder object.
     * @return A RouteFinder object if successful loaded.
     */
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


    /**
     * Write current RouteFinder object out to a file. (public)
     * @param str: The file path to save the RouteFinder object.
     */
    public void save(String str) throws IOException{
        ObjectOutputStream objectStream = null;
        try {
            objectStream = new ObjectOutputStream(new FileOutputStream( str  ));

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


    /**
     * Step through the Route-solving process. (public)
     * @return A boolean value. True if the Maze is solved, otherwise it is False.
     */
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

                    }else{
                        this.route.push(next);
                        temp = next;
                        return false;
                    }

                }
            }


            next = this.maze.getAdjacentTile(temp,Maze.Direction.EAST);
            if(next != null){
 
                if(next.toString() == "x"){
                    this.route.push(next);
                    this.finished = true;
                    temp = next;
                    return true;
                }else if(next.toString() == "."){
                    if(this.blackList.contains(next) || this.route.search(next) != -1 ){
 
                    }else{
                        this.route.push(next);
                        temp = next;
                        return false;
                    }

                }
            }

    
            next = this.maze.getAdjacentTile(temp,Maze.Direction.SOUTH);
            if(next != null){

                if(next.toString() == "x"){
                    this.route.push(next);
                    this.finished = true;
                    temp = next;
                    return true;
                }else if(next.toString() == "."){
                    if(this.blackList.contains(next) || this.route.search(next) != -1 ){
 
                    }else{
                        this.route.push(next);
                        temp = next;

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

    }


    /**
     * Return a string representation of the Route-Solving current state. (public)
     * @return The string representation of the Route-Solving current state.
     */
    public String toString(){


        String all = "";
        for (int i = this.maze.getLineno()-1; i >= 0; i--){
            String split = "";
            for (int ii = 0; ii < this.maze.getColno(); ii ++){
                

                if (   this.blackList.contains(  this.maze.getTileAtLocation(this.maze.setCoord(ii,i))  )   ){
                    split = split + "-";
                }else if (  this.route.search(  this.maze.getTileAtLocation(this.maze.setCoord(ii,i))  ) != -1  ){
                    split = split + "*";
                }else {
                    split = split + this.maze.getTileAtLocation(this.maze.setCoord(ii,i)).toString();
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