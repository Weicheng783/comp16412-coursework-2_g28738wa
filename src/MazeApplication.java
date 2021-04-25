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
// import java.awt.Desktop;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import java.io.FileWriter;
import java.io.BufferedWriter;

//so the whole scene’s backgroundcolor is Green, We put three Blue Vboxes on it which covers the top half of //the scene,  so that the rest part of the scene remains green which is the second rectangle showing in //the example. 

public class MazeApplication extends Application implements Serializable{  
	public List<HBox> many = new LinkedList<> ();
	public List<HBox> boxes = new LinkedList<> ();
    // public List<Text> textList = new LinkedList<> ();
    // public List<Rectangle> rectangleList = new LinkedList<> ();
    public Maze test;

    public RouteFinder Route;
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
                    System.out.println(file.getAbsolutePath());
                    System.out.println(test.toString());
                    // test.setMaze();
                    Route = new RouteFinder(test);
                    // System.out.println(bbb.isFinished());
                    // bbb.save("savedmazetest");
                    // RouteFinder ccc = bbb.load("savedmazetest");
                    // System.out.println( ccc.toString() );
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
            FileChooser objfileChooser = Visual.filechooser();
            
            objfileChooser.setTitle("Select a RouteFinder object (.obj)");
            objfileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("OBJ", "*.obj")
            );
            File objfile = objfileChooser.showOpenDialog(stage);

            if(objfile != null){

                System.out.println("The objfile has opened!");

                try{
                    Route = Route.load(objfile.getAbsolutePath());
                    test = Route.getMaze();
                    // Maze tt = new Maze();


                    String all = "";
                    for (int i = 0; i < test.getTiles().size(); i++){
                        String split = "";
                        for (int ii = 0; ii < test.getTiles().get(i).size(); ii ++){
                            split = split + test.getTiles().get(i).get(ii).toString();
                            // if(ii == test.getTiles().get(i).size()){
                                // split = split + "\n";
                            // }
                        }
                        if (i != test.getTiles().size()-1 ){
                            split = split + "\n";
                        }
                        all = all + split;
                    }

                    System.out.println(all);
                    System.out.println(System.getProperty("user.dir"));
                    // FileOutputStream fos = new FileOutputStream(folder + File.separator + file);
                    File file = new File(System.getProperty("user.dir") + File.separator + "temp.txt");
                    file.createNewFile();
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    out.write(all);
                    out.flush();
                    // file.close(); 
                
                    test = Maze.fromTxt(System.getProperty("user.dir") + File.separator + "temp.txt");

                    // System.out.println(test.toString());

                    // Route = new RouteFinder(test);
                    System.out.println(test.getTiles());

                    if(test == null){
                        System.out.println("test is empty");
                    }else{
                        System.out.println(test);
                    }

                    // System.out.println(Route.toString());    
                    // System.out.println(Route.getBlackList().toString()); 
                    // System.out.println(Route.getRoute().toString()); 
                    // System.out.println(test.getTiles().size());
                    // System.out.println( Maze.setCoord(8,0).getX() );
                    // System.out.println( Route.getMaze().getTileAtLocation(tt.setCoord(8,0)).toString() );
                    // System.out.println(test.testt.size());  
                    // System.out.println(test.getTiles().get(0).size());
                    // loadMaze();
                    updateRoute();
                    start(stage);
                }catch(IOException ex){
                    System.out.println("Error: IOException happened.");
                }catch(InvalidMazeException ex){
                    System.out.println("Error: InvalidMazeException happened.");

                }
                
                // catch(NoRouteFoundException ex){
                //     System.out.println("Error: The given maze has no solution found, please check your maze.");
                // }

            }else{
                System.out.println("No objfile is loaded or the objfile is empty.");
            }           

        });

        Button saveRt = Visual.btn();
        saveRt.setText("Save Route");
        saveRt.setOnAction(e->{

            System.out.println("Save Route Button.");
     

            if(Route != null){


                try{
                    FileChooser objfileSaver = Visual.filechooser();
                
                    objfileSaver.setTitle("Select a place to save the RouteFinder object (.obj)");
                    // objfileSaver.getExtensionFilters().addAll(
                    //     new FileChooser.ExtensionFilter("OBJ", "*.obj")
                    // );
                    File savefile = objfileSaver.showSaveDialog(stage);

                    System.out.println(savefile.getAbsolutePath()+".obj");
                    System.out.println("The objfile has opened!");

                    Route.save(savefile.getAbsolutePath()+".obj");

                    // System.out.println(test.toString());

                    // Route = new RouteFinder(test);
 
                    // updateRoute();
                    start(stage);
                }catch(IOException ex){
                    System.out.println("Error: IOException happened.");
                }catch(InvalidMazeException ex){
                    System.out.println("Error: InvalidMazeException happened.");

                }catch(NullPointerException ex){
                    System.out.println("Null Pointer Exception, nothing really stored.");
                }
                
                // catch(NoRouteFoundException ex){
                //     System.out.println("Error: The given maze has no solution found, please check your maze.");
                // }


            }else{
                System.out.println("You wrote nothing to this file, this file is empty, so you can not do this.");
            }                       

        });

        Button step = Visual.btn();
        step.setText("Step");
        step.setOnAction(e->{
            System.out.println("Step Button.");

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

    public void updateRoute(){

        boxes.clear();

        for(int i=Route.getMaze().getTiles().size()-1; i>=0; i--){ //hang shu
            for(int ii=0; ii<Route.getMaze().getTiles().get(i).size(); ii++){ //lie shu
                System.out.println(i);
                System.out.println(ii);

                System.out.println(  Route.getMaze().getTileAtLocation(Route.getMaze().setCoord(ii,i)).toString()  );
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
