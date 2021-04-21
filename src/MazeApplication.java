import maze.*;
import maze.routing.*;
import maze.visualisation.*;

import javafx.application.Application;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.io.Serializable;

import javafx.event.ActionEvent;

import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.Background; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Polygon; 
import javafx.scene.shape.Circle; 

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.stage.Stage; 
import javafx.geometry.Pos; 

import javafx.scene.input.KeyEvent;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
// import java.awt.Desktop;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

//so the whole scene’s backgroundcolor is Green, We put three Blue Vboxes on it which covers the top half of //the scene,  so that the rest part of the scene remains green which is the second rectangle showing in //the example. 

public class MazeApplication extends Application implements Serializable{  
	public List<HBox> many = new LinkedList<> ();
	public List<HBox> boxes = new LinkedList<> ();
    // public List<Text> textList = new LinkedList<> ();
    // public List<Rectangle> rectangleList = new LinkedList<> ();
    public Maze test;
    // private final Desktop desktop = Desktop.getDesktop();


    // public static void main(String args[]) throws InvalidMazeException, IOException {
    //     Maze test = Maze.fromTxt("/home/csimage/GitRepos/comp16412-coursework-2_g28738wa/resources/mazes/maze1.txt");
    //     System.out.println(test.toString());
    //     // test.setMaze();
    //     RouteFinder bbb = new RouteFinder(test);
    //     System.out.println(bbb.isFinished());
    //     bbb.save("savedmazetest");
    //     RouteFinder ccc = bbb.load("savedmazetest");
    //     System.out.println( ccc.toString() );


    // }


    @Override
    public void start (Stage stage) throws InvalidMazeException, IOException {


        FileChooser fileChooser = Visual.filechooser();


        Button loadMap = Visual.btn();
        loadMap.setText("Load Map");
        loadMap.setOnAction(e->{

            System.out.println("Load Map Button.");
            fileChooser.setTitle("Select a Maze");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
            File file = fileChooser.showOpenDialog(stage);

            if(file != null){

                System.out.println("The file has opened!");

                try{
                    test = Maze.fromTxt(file.getAbsolutePath());
                    // this.test = test;
                    System.out.println(test.toString());
                    // test.setMaze();
                    RouteFinder bbb = new RouteFinder(test);
                    System.out.println(bbb.isFinished());
                    bbb.save("savedmazetest");
                    RouteFinder ccc = bbb.load("savedmazetest");
                    System.out.println( ccc.toString() );
                    loadMaze();
                    // test = null;
                    start(stage);
                }catch(IOException ex){
                    System.out.println("Error: IOException happened.");
                }catch(InvalidMazeException ex){
                    System.out.println("Error: InvalidMazeException happened.");

                }
                
                // catch(NoRouteFoundException ex){
                //     System.out.println("Error: The given maze has no solution found, please check your maze.");
                // }


            }

        });


        Button loadRt = Visual.btn();
        loadRt.setText("Load Route");
        loadRt.setOnAction(e->{

            System.out.println("Load Route Button.");
        });

        Button saveRt = Visual.btn();
        saveRt.setText("Save Route");
        saveRt.setOnAction(e->{

            System.out.println("Save Route Button.");
        });

        Button step = Visual.btn();
        step.setText("Step");
        step.setOnAction(e->{

            System.out.println("Step Button.");
        });


        VBox maze = Visual.vbox(); 
        maze.setAlignment(Pos.CENTER); 
        maze.getChildren().addAll(boxes);


        VBox root = Visual.vbox(); 

        // root.setBackground(1d2d2c); 
        root.setStyle("-fx-background-color:White");

        // root.setFill(Color.BLUE);
        root.setAlignment(Pos.CENTER); 
        root.getChildren().addAll(loadMap ,loadRt, saveRt, maze, step); 
        // bp.setTop(root);

        VBox root2 = Visual.vbox();

        root2.getChildren().addAll();
        //bp.setLeft(leftVbox);
        VBox group = Visual.vbox();
        group.getChildren().addAll(root,root2);

        // This is the scene 

        Scene scene = new Scene(group); 
        //,  600, 500, Color.GREEN

        // Scene sceneb = new Scene(bp,  600, 500, Color.GREEN); 

        stage.setScene(scene); 
        // stage.setScene(sceneb);   

        stage.setTitle("Maze");  

        stage.show();  

    } 


    public static void main(String args[]) { 
        launch(args);
    }

    public void loadMaze(){

        boxes.clear();

        for(int i=test.lineno-1; i>=0; i--){ //hang shu
            for(int ii=0; ii<test.colno; ii++){ //lie shu

                if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "#"){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.web("#895B35")); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);
                }else if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "."){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.PINK); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);       
                }else if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "x"){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.GREEN); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);   
                    // Text t = Visual.text();
                    // t.setText("X");
                    // t.setFont(new Font(15));
                    // HBox HB = Visual.hbox();
                    // HB.getChildren().addAll(t);
                    // many.add(HB);   
                }else if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "e"){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.PURPLE); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);
                    // Text t = Visual.text();
                    // t.setText("e");
                    // t.setFont(new Font(20));
                    // HBox HB = Visual.hbox();
                    // HB.getChildren().addAll(t);
                    // many.add(HB);
                }else{
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.RED); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);         
                }

            }
            HBox a = Visual.hbox();
            a.setAlignment(Pos.CENTER);
            a.getChildren().addAll(many);
            many.clear();
            boxes.add(a);

        }
    }

}
