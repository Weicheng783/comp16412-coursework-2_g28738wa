import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Maze {
    // private Tile entrance;
    // private Tile exit;
    // private List<List<Tile>> tiles;


    private void Maze(){}

    public static Maze fromTxt(String path){
        try (
            BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path)
            )
        ) {
            String line = bufferedReader.readLine();
            // if (!line.strip().equals("{")) return null;
            // line = bufferedReader.readLine();
            while (line != null) {
                // String[] parts = line.strip().split(":");
                // if (parts.length == 1) break;
                // String clean_value = parts[1].split(",")[0].strip();
                System.out.println(line);
                line = bufferedReader.readLine();
                
            }
        } catch (FileNotFoundException e) {
             System.out.println("Error: Could not open " + path);
        } catch (IOException e) {
             System.out.println("Error: IOException when reading "+ path);
        }return null;
    }
}