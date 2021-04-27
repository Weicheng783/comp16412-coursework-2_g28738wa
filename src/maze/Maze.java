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
import java.io.Serializable;

/**
 * Maze class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class Maze implements Serializable{
    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles = new LinkedList<>();

    /**
     * Field testt: from List<String>, it is the linkedlist of the individual Tile object's string representation. (public)
     */
    public List<String> testt = new LinkedList<>();

    /**
     * Field lineno: from int, it is the total row numbers of the whole maze. (public)
     */
    public static int lineno = 0;

    /**
     * Field colno: from int, it is the total column numbers of the whole maze. (public)
     */
    public static int colno = 0;

    private static boolean firstenter = true;

    /**
     * Field noofchars: from int, it is the number of Tile characters in a row. (public)
     */
    public static int noofchars = 0;

    /**
     * The private Constructor of the Maze class. (private)
     */
    private Maze(){}

    /** import maze object from 'txt' file, stores Coordinates of each Tile object. (public)
     *  @param path: txt file path, it is a String object.
     *  @return Return a Maze object if the layout of the maze is valid, otherwise throw exceptions.
     */
    public static Maze fromTxt(String path) throws InvalidMazeException {
        firstenter = true;

        Maze a = new Maze();
        
        try (
            BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path)
            )
        ) {
            lineno = 0;
            String line = bufferedReader.readLine();

            while (line != null) {

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


                    for (int m = 0; m < length; m++){
                            Tile temp = Tile.fromChar(line.toCharArray()[m]);

                            tiiles.add(temp);

                            if (line.toCharArray()[m] == 'e'){
                                if(a.entrance == null){
                                    a.entrance = temp;
                                }else{
                                    throw new MultipleEntranceException("A maze can not have multiple entrance.");
                                }
                            }else if(line.toCharArray()[m] == 'x'){
                                if(a.exit == null){
                                    a.exit = temp;
                                }else{
                                    throw new MultipleExitException("A maze can not have multiple exit.");
                                }
                            }else if (line.toCharArray()[m] != 'e' && line.toCharArray()[m] != 'x' && line.toCharArray()[m] != '.' && line.toCharArray()[m] != '#'){
                                throw new InvalidMazeException("This maze contains an unknown tile (invalid char) '" + line.toCharArray()[m] + "', please check again.");
                            }
                    }
                
                a.tiles.add(lineno, tiiles);

                lineno = lineno + 1;

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
                    a.tiles.get(lineno-1-m).get(n).coords = a.setCoord(n, m); //original m,n
                    colno = a.tiles.get(m).size();

                }
            }
            System.out.println(a.tiles);

        } catch (FileNotFoundException e) {
             System.out.println("Error: Could not open " + path);
        } catch (IOException e) {
             System.out.println("Error: IOException when reading "+ path);
        }

        return a;
    }

    /**
     * Get the Maze's column number. (public)
     * @return The Maze's column number.
     */
    public int getColno(){
        return colno;
    }

    /**
     * Get the Maze's row number. (public)
     * @return The Maze's row number.
     */
    public int getLineno(){
        return lineno;
    }

    /**
     * Get a specific Maze's Entrance. (public)
     * @return The specific Maze's Entrance.
     */
    public Tile getEntrance(){
        return entrance;
    }

    /**
     * Get a specific Maze's Exit. (public)
     * @return The specific Maze's Exit.
     */
    public Tile getExit(){
        return exit;
    }

    /**
     * Set a specific Maze's Entrance. (private)
     * @param tile: The specific Maze's Entrance.
     */
    private void setEntrance(Tile tile) throws MultipleEntranceException{
        try{
            if(entrance == null){
                for(int i=0; i<getTiles().size(); i++){
                    if( getTiles().get(i).contains(tile) ){
                        entrance = tile;
                        break;
                    }else{
                        if(i == getTiles().size()-1){
                            System.out.println("This tile is not in the Maze, so you can not setEntrance using this tile.");
                        }
                    }
                }
            }else{
                
                throw new MultipleEntranceException("Entrance already exists, you can not set another.");
            }
        }catch(MultipleEntranceException e){
            System.out.println("Entrance already been set, you can not set another.");
            throw new MultipleEntranceException("Entrance already exists, you can not set another.");
        }

        
    }

    /**
     * Set a specific Maze's Exit. (private)
     * @param tile: The specific Maze's Exit.
     */
    private void setExit(Tile tile) throws MultipleExitException{
        try{
            if (exit == null){
                for(int i=0; i<getTiles().size(); i++){
                    if( getTiles().get(i).contains(tile) ){
                        exit = tile;
                        break;
                    }else{
                        if(i == getTiles().size()-1){
                            System.out.println("This tile is not in the Maze, so you can not setExit using this tile.");
                        }
                    }
                }
            }else{
                
                throw new MultipleExitException("Exit already exists, you can not set another.");
            }
        }catch(MultipleExitException e){
            System.out.println("Exit already exists, you can not set another.");
            throw new MultipleExitException("Exit already exists, you can not set another.");
        }


    }

    /**
     * Get all Tile objects in a 2D array. (public)
     * @return All tile objects in a 2D array.
     */
    public List<List<Tile>> getTiles(){

        return tiles;

    }

    /**
     * Return a string representation of the tile objects. (public)
     * @return A string representation of tile objects.
     */
    public String toString(){
        String all = "";
        for (int i = lineno-1; i >= 0; i--){
            String split = "";
            for (int ii = 0; ii < colno; ii ++){
                split = split + getTileAtLocation(setCoord(ii,i)).toString();
            }
            if (i != 0){
                split = split + "\n";
            }
            all = all + split;
        }
        return all;

    }

    /**
     * Get adjacentTile infomation from a specific tile and the wanted direction. (public)
     * @return a Tile object which is the required adjacent tile.
     * @param tile: The specific raw tile object
     * @param dir: The wanted direction.
     */
    public Tile getAdjacentTile(Tile tile, Direction dir){
        if(dir == Direction.SOUTH){
            if(getTileLocation(tile).getY() == 0){
                System.out.println("You can not go below this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX() , getTileLocation(tile).getY()-1 ));
            }
        }else if(dir == Direction.NORTH){
           if(getTileLocation(tile).getY() == lineno-1){
                System.out.println("You can not go beyond this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX() , getTileLocation(tile).getY()+1 ));
            }
        }else if(dir == Direction.WEST){
           if(getTileLocation(tile).getX() == 0){
                System.out.println("You can not go below this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX()-1 , getTileLocation(tile).getY() ));
            }
        }else if(dir == Direction.EAST){
           if(getTileLocation(tile).getX() == colno-1){
                System.out.println("You can not go beyond this axis.");
                return null;
            }else{
                return getTileAtLocation(setCoord( getTileLocation(tile).getX()+1 , getTileLocation(tile).getY() ));
            }
        }else{
            System.out.println("emmm, received no direction.");
            return null;
        }

    }

    /**
     * Return a Coordinate object given a specific tile. (public)
     * @return A Coordinate object of the specific tile location.
     * @param tile: Specific tile needed to find location.
     */
    public Coordinate getTileLocation(Tile tile){
        return tile.coords;
    }

    /**
     * Give a coordinate, return a tile object which is located at this place. (public)
     * @return The tile object which is located at this place.
     * @param coords: Specific coordinate object.
     */
    public Tile getTileAtLocation(Coordinate coords){
        return tiles.get(tiles.size()-1-coords.getY()).get(coords.getX());

    }

    /**
     * Give a pair of points (x,y), return a new created coordinate object. (public)
     * @return The new created coordinate object.
     * @param xax: x-coordinate.
     * @param yay: y-coordinate.
     */
    public Coordinate setCoord(int xax, int yay){
        Coordinate a = new Coordinate(xax,yay);

        return a;

    }


    /**
    * Coordinate class (Nested class in Maze class). This class is a nested class in Maze class, it serves the coordinates part. (public)
    * @author Weicheng Ao
    * @version 1.0
    * @since 1.0
    */
    public class Coordinate implements Serializable{

        private int x;
        private int y;

        /** The constructor of the Coordinate class. (public) */
        public Coordinate(int xx, int yy){
            x = xx;
            y = yy;
        }

        /** Get x-coordinate. (public) */
        public int getX(){
            return x;
        }

        /** Get y-coordinate. (public) */
        public int getY(){
            return y;
        }

        /** Returns the string representation of the coordinates. (public) */
        public String toString(){
            return "(" + x + ", " + y +")";
        }

    }

    /**
     * An enum which contains four direction constants. (public)
     */
    public enum Direction {
        NORTH,SOUTH,EAST,WEST;
    }

}