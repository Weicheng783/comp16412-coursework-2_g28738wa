package maze;

import java.io.Serializable;

/**
 * Tile class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class Tile implements Serializable{
    private Type type;

    /**
     * This field is Maze.Coordinate, we fetch and store coordinates in Maze.Coordinate. (protected)
     * @param coords: Coordinates of a Tile object.
     */
    protected Maze.Coordinate coords;

    /**
     * The Tile class constructor. (private)
     * @param typee: Type of the Tile.
     */
    private Tile(Type typee){
        type = typee;
    }

    /** This is enum which contains the four Type constants. (public) */
    public enum Type {
        CORRIDOR,ENTRANCE,EXIT,WALL;
    }

    /**
     * This method 'fromChar' takes a letter, and try to find it's meaning, returns the Tile object if the char has meaning. (protected)
     * @param letter: A single char(can be . # e x).
     * @return A tile object if the single char is in .#ex, otherwise it returns null.
     */
    protected static Tile fromChar(char letter){
        // Tile a = new Tile();

        if(letter == '.'){
            Tile a = new Tile(Type.CORRIDOR);
            // a.type = Type.CORRIDOR;
            return a;
        }else if(letter == '#'){
            Tile a = new Tile(Type.WALL);
            return a;
        }else if(letter == 'e'){
            Tile a = new Tile(Type.ENTRANCE);
            return a;
        }else if(letter == 'x'){
            Tile a = new Tile(Type.EXIT);
            return a;
        }else{
            return null;
        }

    }

    /** Get a specific Tile type. (public)
     * @return A constant in enum Type which is pre-stored in a specific Tile.
     */
    public Type getType(){
        return type;
    }

    /** Check if a specific tile object can be navigated. (public)
      * @return True if it can be navigated, false when the type is WALL otherwise.
      */
    public boolean isNavigable(){
        if(getType()==Type.WALL){
            return false;
        }else{
            return true;
        }

    }

    /** Returns a string representation of the type constants. (public)
    * @return The string representation of the type constants(#,e,x,.). But if the tile has no type, it returns null.
    */
    public String toString(){
        if(getType()==Type.WALL){
            return "#";
        }else if(getType()==Type.ENTRANCE){
            return "e";
        }else if(getType()==Type.EXIT){
            return "x";
        }else if(getType()==Type.CORRIDOR){
            return ".";
        }else{
            return null;
        }
    }




}