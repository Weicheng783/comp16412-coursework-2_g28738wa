import maze.*;
import maze.routing.*;
import maze.visualisation.*;

import javafx.application.Application;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

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

import java.io.IOException;

//so the whole scene’s backgroundcolor is Green, We put three Blue Vboxes on it which covers the top half of //the scene,  so that the rest part of the scene remains green which is the second rectangle showing in //the example. 

public class MazeApplication extends Application {  
	public List<Rectangle> many = new LinkedList<> ();
	public List<HBox> boxes = new LinkedList<> ();

@Override
public void start (Stage stage) {  


Button in = new Button();
in.setText("Enter Maze Solver");
in.setOnAction(e->{
	// @Override
	System.out.println("Enter Button Clicked.");

    Visual open = new Visual();
    try{
        open.start(new Stage());
    }catch(InvalidMazeException ex){
        System.out.println("Error: Invalid Maze found, please check it again. ");
    }catch(IOException ex){
        System.out.println("Error: IOException when loading maze.");
    }
    
    stage.hide();
});


// Button loadRt = new Button();
// loadRt.setText("Load Route");
// loadRt.setOnAction(e->{
// 	// @Override
// 	System.out.println("Load Route Button.");
// });

// Button saveRt = new Button();
// saveRt.setText("Save Route");
// saveRt.setOnAction(e->{
// 	// @Override
// 	System.out.println("Save Route Button.");
// });

// Button step = new Button();
// step.setText("Step");
// step.setOnAction(e->{
// 	// @Override
// 	System.out.println("Step Button.");
// });


// for(int j=0; j<10; j++){ //hang shu
// 	for(int i=0; i<20; i++){ //lie shu
// 		Rectangle R1 = new Rectangle (20,30,20,30); 
// 		R1.setFill (Color.SLATEBLUE); 
// 		R1.setArcHeight(15);
// 		R1.setArcWidth(15);
// 		// R1.setArcWidth(10);
// 		Rectangle R2 = new Rectangle (20,30,20,30); 
// 		R2.setFill(Color.GREEN);
// 		R2.setArcHeight(15);
// 		R2.setArcWidth(15);

// 		many.add(R1);
// 		many.add(R2);
// 	}
// 	HBox a = new HBox();
// 	a.setAlignment(Pos.CENTER);
// 	a.getChildren().addAll(many);
// 	many.clear();
// 	boxes.add(a);

// }


// VBox maze = new VBox(0); 
// maze.setAlignment(Pos.CENTER); 
// maze.getChildren().addAll(boxes);



VBox root = new VBox(0); 


root.setStyle("-fx-background-color:White");


root.setAlignment(Pos.CENTER); 
root.getChildren().addAll(in); 


VBox root2 = new VBox();

root2.getChildren().addAll();

VBox group = new VBox();
group.getChildren().addAll(root,root2);


// This is the scene 

Scene scene = new Scene(group, 500,300); 

scene.setOnKeyPressed(e->{


});

stage.setScene(scene);  

stage.setTitle("Maze Solver -- Welcome Page");  

stage.show();  

} 

 public static void main(String args[]) { 
	launch(args);
 }

}
