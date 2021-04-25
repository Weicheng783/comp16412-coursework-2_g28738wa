package maze.visualisation;

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
import javafx.scene.layout.StackPane;
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

public class Visual implements Serializable{  
    //extends Application
    // public Maze maze;

    public static Button btn(){
        Button btn = new Button();
        btn.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        return btn;
    }

    public static VBox vbox() {
        VBox vbox = new VBox(); 
        return vbox;
    }

    public static HBox hbox(){
        HBox hbox = new HBox();
        return hbox;
    }

    public static FileChooser filechooser(){
        FileChooser filechooser = new FileChooser();
        return filechooser;
    }

    public static Rectangle rectangle(){
        Rectangle RTG = new Rectangle (50,50,50,50); 
        return RTG;
    }

    public static Text text(){
        Text text = new Text(40, 40, "");
        text.setFont(new Font(40));
        return text;
    }

    public static Text pathtext(){
        Text pathtext = new Text(40, 40, "");
        pathtext.setFont(new Font(25));
        return pathtext;
    }

    public static StackPane stackpane(){
        StackPane stackpane = new StackPane();
        return stackpane;
    }
    // public Scene scene(){
    //     Scene scene = new Scene(group); 

    // }

    // public void setMaze(Maze mazee){

    // }


}
