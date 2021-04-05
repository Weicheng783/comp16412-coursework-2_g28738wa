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


public class Maze {
    private Tile entrance;
    private Tile exit;
    private List<List<Tile>> tiles = new LinkedList<>();

    private List<String> testt = new LinkedList<>();

    

    // private static Tile[][] tiless;

    private static int lineno = 0;
    private static int colno = 0;

    private void Maze(){}

    public static Maze fromTxt(String path){
        
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
                // System.out.println(length);
                // for (int i = 0; i < lineno; i++){

                    for (int m = 0; m < length; m++){

                            // System.out.println(length);
                            // System.out.println(lineno);
                            // System.out.println(m);

                            // a.testt.add("nhas   sa  asd a453");
                            // a.testt.add("good moring!");
                            // System.out.println(a.testt);

                            // System.out.println(a.tiiles);
                            
                            // a.tiles[1][1]=Tile.fromChar(line.toCharArray()[1]);
                            // System.out.println(Tile.fromChar(line.toCharArray()[m]).getType());
                            tiiles.add(Tile.fromChar(line.toCharArray()[m]) );
                            // Coordinate.Coordinate(lineno,m);
                            // System.out.println(a.tiiles);
                            
                        // Coordinate [lineno][m]
                    }
                // }
                // Maze.setMaze(tiless);
                
                a.tiles.add(lineno, tiiles);
                // System.out.println(a.tiles);
                // a.tiiles.clear();
                lineno = lineno + 1;

                //  System.out.println("Break!");

                line = bufferedReader.readLine();
                
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

        return a;
    }

    public Tile getEntrance(){
        return entrance;
    }

    public Tile getExit(){
        return exit;
    }

    private void setEntrance(Tile tile){
        entrance = tile;
    }

    private void setExit(Tile tile){
        exit = tile;
    }

    public List<List<Tile>> getTiles(){

        return tiles;

    }

    public String toString(){
        return "";

    }

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

    public Coordinate getTileLocation(Tile tile){
        // return tile.Coordinate;
        // Coordinate a = new Coordinate();
        // a.x = tile.
        return tile.coords;
    }

    public Tile getTileAtLocation(Coordinate coords){
        return tiles.get(coords.getX()).get(coords.getY());

    }

    public Coordinate setCoord(int xax, int yay){
        Coordinate a = new Coordinate();
        a.x = xax;
        a.y = yay;

        return a;

    }

    class Coordinate{

        private int x;
        private int y;

        public void Coordinate(int xx, int yy){
            // Coordinate a = new Coordinate();
            x = xx;
            y = yy;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public String toString(){
            return "(" + x + "," + y +")";
        }

    }

    public enum Direction {
        NORTH,SOUTH,EAST,WEST;
    }

}