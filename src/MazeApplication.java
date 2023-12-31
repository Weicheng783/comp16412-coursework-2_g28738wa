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
import javafx.scene.layout.StackPane;
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

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * MazeApplication class (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class MazeApplication extends Application implements Serializable{  

    /**
     * The Constructor of the MazeApplication. (public)
     */
    public MazeApplication(){}

    /**
     * Field many: from HBox, it is the helper of the individual Tile object. (public)
     */
	public List<HBox> many = new LinkedList<> ();

    /**
     * Field boxes: from HBox, it is the helper of the whole Maze map (Tiles) object. (public)
     */
	public List<HBox> boxes = new LinkedList<> ();

    /**
     * Field test: from Maze, it defines a new Maze object for storing maze. (public)
     */
    public Maze test;

    /**
     * Field Route: from RouteFinder, it defines a new RouteFinder object for storing Route. (public)
     */
    public RouteFinder Route;


    /**
     * The start method of JavaFX. (public)
     * @param stage: JavaFX stage object.
     * @exception InvalidMazeException if the maze is invalid, throws an InvalidMazeException.
     * @exception IOException Throwed if IO exceptions occur.
     */
    @Override
    public void start (Stage stage) throws InvalidMazeException, IOException {


        FileChooser fileChooser = Visual.filechooser();


        Button loadMap = Visual.btn();
        loadMap.setText("Load Map");
        loadMap.setOnAction(e->{


            fileChooser.setTitle("Select a Maze");

            File file = fileChooser.showOpenDialog(stage);

            if(file != null){

                System.out.println("The file has opened!");

                try{
                    test = Maze.fromTxt(file.getAbsolutePath());

                    // System.out.println(file.getAbsolutePath());
                    // System.out.println(test.toString());

                    Route = new RouteFinder(test);

                    loadMaze();

                    start(stage);
                    
                }catch(IOException ex){
                    System.out.println("Error: IOException happened.");
                }catch(InvalidMazeException ex){
                    System.out.println("Error: InvalidMazeException happened.");

                }

            }

        });


        Button loadRt = Visual.btn();
        loadRt.setText("Load Route");
        loadRt.setOnAction(e->{


            FileChooser objfileChooser = Visual.filechooser();
            
            objfileChooser.setTitle("Select a RouteFinder object to begin this journey");
            // objfileChooser.getExtensionFilters().addAll(
            //     new FileChooser.ExtensionFilter("OBJ", "*.obj")
            // );
            File objfile = objfileChooser.showOpenDialog(stage);

            if(objfile != null){

                try{
                    Route = Route.load(objfile.getAbsolutePath());
                    test = Route.getMaze();


                    String all = "";
                    for (int i = 0; i < test.getTiles().size(); i++){
                        String split = "";
                        for (int ii = 0; ii < test.getTiles().get(i).size(); ii ++){
                            split = split + test.getTiles().get(i).get(ii).toString();

                        }
                        if (i != test.getTiles().size()-1 ){
                            split = split + "\n";
                        }
                        all = all + split;
                    }

                    // System.out.println(all);
                    // System.out.println(System.getProperty("user.dir"));

                    File file = new File(System.getProperty("user.dir") + File.separator + "temp.txt");
                    file.createNewFile();
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    out.write(all);
                    out.flush();

                    test = Maze.fromTxt(System.getProperty("user.dir") + File.separator + "temp.txt");

                    updateRoute();
                    start(stage);
                }catch(IOException ex){
                    System.out.println("Error: IOException happened.");
                }catch(InvalidMazeException ex){
                    System.out.println("Error: InvalidMazeException happened.");
                }catch(NullPointerException ex){
                    System.out.println("Null pointer exception, this means the file is empty or otherwise.");
                }

            }else{
                System.out.println("No objfile is loaded or the objfile is empty.");
            }           

        });

        Button saveRt = Visual.btn();
        saveRt.setText("Save Route");
        saveRt.setOnAction(e->{

            if(Route != null){

                try{
                    FileChooser objfileSaver = Visual.filechooser();
                
                    objfileSaver.setTitle("Select or Type a place to save the RouteFinder object");

                    File savefile = objfileSaver.showSaveDialog(stage);

                    System.out.println("The objfile has opened!");

                    Route.save(savefile.getAbsolutePath());

                    start(stage);
                }catch(IOException ex){
                    System.out.println("Error: IOException happened.");
                }catch(InvalidMazeException ex){
                    System.out.println("Error: InvalidMazeException happened.");
                }catch(NullPointerException ex){
                    System.out.println("Null Pointer Exception, nothing really stored.");
                }

            }else{
                System.out.println("You wrote nothing to this file, this file is empty, so you can not do this.");
            }                       

        });

        Button step = Visual.btn();
        step.setText("Step");
        step.setOnAction(e->{

            if(Route != null){
                try{
                    Route.step();
                    updateRoute();
                    start(stage);
                }catch(NoRouteFoundException ex){
                    System.out.println("This particular maze has no route found after attempting several steps.");
                }catch(InvalidMazeException ex){
                    System.out.println("This maze is invalid, please check it again.");
                }catch(IOException ex){
                    System.out.println("IOException thrown.");
                }

            }else{
                System.out.println("No maze map loaded.");
            }

        });


        VBox maze = Visual.vbox(); 
        maze.setAlignment(Pos.CENTER); 
        maze.getChildren().addAll(boxes);
        maze.setStyle("-fx-border-width:3; -fx-border-style: solid inside; -fx-border-color: purple; -fx-border-insets: 5; -fx-border-radius: 5;");

        VBox root = Visual.vbox(); 

        root.setStyle("-fx-background-color:White; -fx-border-width:3; -fx-border-style: solid inside; -fx-border-color: blue; -fx-border-insets: 5; -fx-border-radius: 5;");

        root.setAlignment(Pos.CENTER); 
        root.getChildren().addAll(loadMap ,loadRt, saveRt, maze, step); 


        VBox root2 = Visual.vbox();

        Text notice = Visual.text();
        notice.setText("Waiting For a Valid Maze then. Ready to solve it.");
        notice.setFont(new Font(18));

        Text notice1 = Visual.text();
        notice1.setText("Note:\nRed '-' means this way is blocked, \nPurple '*' means the current finding path, \nAfter the maze solved, the maze will remain no change.");
        notice1.setFont(new Font(18));
        notice1.setUnderline(true);
        notice1.setFill(Color.RED);

        Text notice2 = Visual.text();
        notice2.setText("* Weicheng Ao * 2021 *");
        notice2.setFont(new Font(20));
        notice2.setFill(Color.GREEN);

        root2.setAlignment(Pos.CENTER);

        root2.getChildren().addAll(notice,notice1,notice2);

        VBox group = Visual.vbox();
        group.getChildren().addAll(root,root2);


        // This is the scene 
        Scene scene = new Scene(group);

        stage.setScene(scene); 

        stage.setTitle("Maze");  

        stage.show();  

    } 


    /**
     * The main method of JavaFX, user interface. (public)
     */
    public static void main(String args[]) {
        launch(args);
    }

    /**
     * The method of loadMaze, initialise the whole Maze if possible. (public)
     */
    public void loadMaze(){

        boxes.clear();

        for(int i=test.getLineno()-1; i>=0; i--){ // column number
            for(int ii=0; ii<test.getColno(); ii++){ // row number

                if(test.getTileAtLocation(test.setCoord(ii,i)).toString() == "#"){
                    Rectangle R1 = Visual.rectangle();
                    R1.setFill (Color.web("#895B35"));
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);
                }else if(test.getTileAtLocation(test.setCoord(ii,i)).toString() == "."){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.WHITE); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);       
                }else if(test.getTileAtLocation(test.setCoord(ii,i)).toString() == "x"){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.LIGHTGREEN); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    Text text = Visual.text();
                    text.setText("X");
                    text.setStyle("-fx-font-weight:bold"); 
                    StackPane pane = Visual.stackpane();
                    pane.getChildren().addAll(R1,text);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(pane);
                    many.add(HB);   
  
                }else if(test.getTileAtLocation(test.setCoord(ii,i)).toString() == "e"){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.LIGHTBLUE); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    Text text = Visual.text();
                    text.setText("E");
                    text.setStyle("-fx-font-weight:bold"); 
                    StackPane pane = Visual.stackpane();
                    pane.getChildren().addAll(R1,text);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(pane);
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

    /**
     * This method is used to updated the route, serves the main purpose of interface updating. (public)
     */
    public void updateRoute(){

        boxes.clear();

        for(int i=Route.getMaze().getTiles().size()-1; i>=0; i--){ 
            for(int ii=0; ii<Route.getMaze().getTiles().get(i).size(); ii++){ 

                if(Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i)).toString() == "#"){
                    Rectangle R1 = Visual.rectangle(); 
                    R1.setFill (Color.web("#895B35")); 
                    R1.setArcHeight(15);
                    R1.setArcWidth(15);
                    HBox HB = Visual.hbox();
                    HB.getChildren().addAll(R1);
                    many.add(HB);
                }else if(Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i)).toString() == "."){
                    if ( Route.getBlackList().contains(  Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i))  )  ){
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.WHITE);
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        Text text = Visual.pathtext();
                        text.setText("-");
                        text.setFill(Color.RED);
                        text.setStyle("-fx-font-weight:bold"); 
                        StackPane pane = Visual.stackpane();
                        pane.getChildren().addAll(R1,text);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(pane);
                        many.add(HB);
                    }else if(  Route.getRoute().contains(  Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i))  ) == true  ){
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.WHITE); 
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        Text text = Visual.pathtext();
                        text.setText("*");
                        text.setFill(Color.PURPLE);
                        text.setStyle("-fx-font-weight:bold"); 
                        StackPane pane = Visual.stackpane();
                        pane.getChildren().addAll(R1,text);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(pane);
                        many.add(HB);
                    }else{
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.WHITE); 
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(R1);
                        many.add(HB);                          
                    }
     
                }else if(Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i)).toString() == "x"){
                    if ( Route.getRoute().contains(  Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i))  ) == true ){
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.LIGHTGREEN); 
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        Text text = Visual.pathtext();
                        text.setText("*");
                        text.setFill(Color.PURPLE);
                        text.setStyle("-fx-font-weight:bold"); 
                        StackPane pane = Visual.stackpane();
                        pane.getChildren().addAll(R1,text);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(pane);
                        many.add(HB);
                    }else{
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.LIGHTGREEN); 
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        Text text = Visual.text();
                        text.setText("X");
                        text.setStyle("-fx-font-weight:bold"); 
                        StackPane pane = Visual.stackpane();
                        pane.getChildren().addAll(R1,text);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(pane);
                        many.add(HB);                           
                    }
  

                }else if(Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i)).toString() == "e"){
                    if ( Route.getRoute().contains(  Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i))  ) == true ){
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.LIGHTBLUE); 
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        Text text = Visual.pathtext();
                        text.setText("*");
                        text.setFill(Color.PURPLE);
                        text.setStyle("-fx-font-weight:bold"); 
                        StackPane pane = Visual.stackpane();
                        pane.getChildren().addAll(R1,text);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(pane);
                        many.add(HB);
                    }else{
                        Rectangle R1 = Visual.rectangle(); 
                        R1.setFill (Color.LIGHTBLUE); 
                        R1.setArcHeight(15);
                        R1.setArcWidth(15);
                        Text text = Visual.text();
                        text.setText("E");
                        text.setStyle("-fx-font-weight:bold"); 
                        StackPane pane = Visual.stackpane();
                        pane.getChildren().addAll(R1,text);
                        HBox HB = Visual.hbox();
                        HB.getChildren().addAll(pane);
                        many.add(HB);                      
                    }
 
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
