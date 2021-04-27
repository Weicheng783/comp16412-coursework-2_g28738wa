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

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

/**
 * Visual class in maze.visualisation package (public)
 * @author Weicheng Ao
 * @version 1.0
 * @since 1.0
 */
public class Visual implements Serializable{  


    /**
     * Create a new Button object to use. (public)
     * @return A new JavaFX Button object.
     */
    public static Button btn(){
        Button btn = new Button();
        btn.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        return btn;
    }

    /**
     * Create a new VBox object to use. (public)
     * @return A new JavaFX VBox object.
     */
    public static VBox vbox() {
        VBox vbox = new VBox(); 
        return vbox;
    }

    /**
     * Create a new HBox object to use. (public)
     * @return A new JavaFX HBox object.
     */
    public static HBox hbox(){
        HBox hbox = new HBox();
        return hbox;
    }

    /**
     * Create a new FileChooser object which lets users choose files. (public)
     * @return A new JavaFX FileChooser object.
     */
    public static FileChooser filechooser(){
        FileChooser filechooser = new FileChooser();
        return filechooser;
    }

    /**
     * Create a new Rectangle object to use. (public)
     * @return A new JavaFX Rectangle object.
     */
    public static Rectangle rectangle(){
        Rectangle RTG = new Rectangle (50,50,50,50); 
        return RTG;
    }

    /**
     * Create a new Text(Normal) object to use. (public)
     * @return A new JavaFX Text object.
     */
    public static Text text(){
        Text text = new Text(40, 40, "");
        text.setFont(new Font(40));
        return text;
    }

    /**
     * Create a new Text(Small-fonted) object to use. (public)
     * @return A new JavaFX Text object.
     */
    public static Text pathtext(){
        Text pathtext = new Text(40, 40, "");
        pathtext.setFont(new Font(25));
        return pathtext;
    }

    /**
     * Create a new StackPane object to use. (public)
     * @return A new JavaFX StackPane object.
     */
    public static StackPane stackpane(){
        StackPane stackpane = new StackPane();
        return stackpane;
    }


}
