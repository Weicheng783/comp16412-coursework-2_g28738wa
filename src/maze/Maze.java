package maze;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Maze class
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class Maze {
    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles = new LinkedList<>();

    private List<String> testt = new LinkedList<>();



    // private static Tile[][] tiless;

    private static int lineno = 0;
    private static int colno = 0;
    private static boolean firstenter = true;
    private static int noofchars = 0;

    private void Maze(){}

    /** import maze object from 'txt' file, stores Coordinates of each Tile object. 
     *  @param path: txt file path, it is a String object.
     *  @return Return a Maze object if the layout of the maze is valid, otherwise throw exceptions.
     */
    public static Maze fromTxt(String path) throws InvalidMazeException {

        Maze a = new Maze();
        
        try (
            BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path)
            )
        ) {
            lineno = 0;
            String line = bufferedReader.readLine();
            // if (!line.strip().equals("{")) return null;
            // line = bufferedReader.readLine();
            while (line != null) {
                // String[] parts = line.strip().split(":");
                // if (parts.length == 1) break;
                // String clean_value = parts[1].split(",")[0].strip();
                System.out.println(line);

                List<Tile> tiiles = new LinkedList<>();
                
                int length = line.length();

                if (firstenter == true){
                    noofchars = length;
                    firstenter = false;
                }else{
                    if(length != noofchars){
                        throw new RaggedMazeException("This is a ragged maze, please check that every line contains the same number of tiles.");
                    }
                }

                noofchars = length;
                // System.out.println(length);
                // for (int i = 0; i < lineno; i++){

                    for (int m = 0; m < length; m++){
                            Tile temp = Tile.fromChar(line.toCharArray()[m]);

                            tiiles.add(temp);

                            if (line.toCharArray()[m] == 'e'){
                                if(a.entrance == null){
                                    a.setEntrance(temp);
                                }else{
                                    throw new MultipleEntranceException("A maze can not have multiple entrance.");
                                }
                            }else if(line.toCharArray()[m] == 'x'){
                                if(a.exit == null){
                                    a.setExit(temp);
                                }else{
                                    throw new MultipleExitException("A maze can not have multiple exit.");
                                }
                            }
                    }
                
                a.tiles.add(lineno, tiiles);
                // System.out.println(a.tiles);
                // a.tiiles.clear();
                lineno = lineno + 1;

                //  System.out.println("Break!");

                line = bufferedReader.readLine();
                
            }

            if(a.entrance == null){
                throw new NoEntranceException("No entrance found! A maze must have exactly one entrance.");
            }

            if(a.exit == null){
                throw new NoExitException("No exit found! A maze must have exactly one exit.");
            }

            lineno = a.tiles.size();

            for(int m = lineno-1; m >= 0; m--){
                for(int n = 0; n < a.tiles.get(m).size(); n++){
                    a.tiles.get(m).get(n).coords = a.setCoord(m, n); //This has been fixed.
                    colno = a.tiles.get(m).size();
                    System.out.println(m+","+n+". Success!");
                }
            }
            System.out.println(a.tiles);

        } catch (FileNotFoundException e) {
             System.out.println("Error: Could not open " + path);
        } catch (IOException e) {
             System.out.println("Error: IOException when reading "+ path);
        }
        // throw new RaggedMazeException("HAHA GOOD! YOU THROWED AN EXCEPTION!");
        return a;
    }

    /**
     * Get a specific Maze's Entrance.
     * @return The specific Maze's Entrance.
     */
    public Tile getEntrance(){
        return entrance;
    }

    /**
     * Get a specific Maze's Exit.
     * @return The specific Maze's Exit.
     */
    public Tile getExit(){
        return exit;
    }

    /**
     * Set a specific Maze's Entrance.
     * @param tile: The specific Maze's Entrance.
     */
    private void setEntrance(Tile tile){
        entrance = tile;
    }

    /**
     * Set a specific Maze's Exit.
     * @param tile: The specific Maze's Exit.
     */
    private void setExit(Tile tile){
        exit = tile;
    }

    /**
     * Get all Tile objects in a 2D array.
     * @return All tile objects in a 2D array.
     */
    public List<List<Tile>> getTiles(){

        return tiles;

    }

    /**
     * Return a string representation in the command line.
     * @return A string representation of tile objects.
     */
    public String toString(){
        return "";

    }

    /**
     * Get adjacentTile infomation from a specific tile and the wanted direction.
     * @return a Tile object which is the required adjacent tile.
     * @param tile: The specific raw tile object
     * @param dir: The wanted direction.
     */
    public Tile getAdjacentTile(Tile tile, Direction dir){
        if(dir == Direction.SOUTH){
            if(getTileLocation(tile).getX() == 0){
                System.out.println("You can not go below this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX()-1 , getTileLocation(tile).getY() ));
            }
        }else if(dir == Direction.NORTH){
           if(getTileLocation(tile).getX() == lineno-1){
                System.out.println("You can not go beyond this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX()+1 , getTileLocation(tile).getY() ));
            }
        }else if(dir == Direction.WEST){
           if(getTileLocation(tile).getY() == 0){
                System.out.println("You can not go below this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX() , getTileLocation(tile).getY()-1 ));
            }
        }else if(dir == Direction.EAST){
           if(getTileLocation(tile).getX() == colno-1){
                System.out.println("You can not go beyond this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX() , getTileLocation(tile).getY()+1 ));
            }
        }else{
            System.out.println("emmm, received no direction.");
            return null;
        }

    }

    /**
     * Return a Coordinate object given a specific tile.
     * @return A Coordinate object of the specific tile location.
     * @param tile: Specific tile needed to find location.
     */
    public Coordinate getTileLocation(Tile tile){
        // return tile.Coordinate;
        // Coordinate a = new Coordinate();
        // a.x = tile.
        return tile.coords;
    }

    /**
     * Give a coordinate, return a tile object which is located at this place.
     * @return The tile object which is located at this place.
     * @param coords: Specific coordinate object.
     */
    public Tile getTileAtLocation(Coordinate coords){
        return tiles.get(coords.getX()).get(coords.getY());

    }

    /**
     * Give a pair of points (x,y), return a new created coordinate object.
     * @return The new created coordinate object.
     * @param xax: x-coordinate.
     * @param yay: y-coordinate.
     */
    public Coordinate setCoord(int xax, int yay){
        Coordinate a = new Coordinate();
        a.x = xax;
        a.y = yay;

        return a;

    }

    /**
     * This class is a nested class in Maze class, it serves the coordinates part. 
     */
    class Coordinate{

        private int x;
        private int y;

        /** The constructor of the Coordinate class */
        public void Coordinate(int xx, int yy){
            // Coordinate a = new Coordinate();
            x = xx;
            y = yy;
        }

        /** Get x-coordinate */
        public int getX(){
            return x;
        }

        /** Get y-coordinate  */
        public int getY(){
            return y;
        }

        /** Returns the string representation of the coordinates. */
        public String toString(){
            return "(" + x + "," + y +")";
        }

    }

    /**
     * An enum which contains four direction constants.
     */
    public enum Direction {
        NORTH,SOUTH,EAST,WEST;
    }

}