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

public class Visual extends Application implements Serializable{  
	public List<Rectangle> many = new LinkedList<> ();
	public List<HBox> boxes = new LinkedList<> ();
    public Maze test;
    // private final Desktop desktop = Desktop.getDesktop();

@Override
public void start (Stage stage) throws InvalidMazeException, IOException {
// int status = 0;
// BorderPane bp = new BorderPane();

// This is the sun (Circle) 

// AnchorPane ap = new AnchorPane();


FileChooser fileChooser = new FileChooser();


Button loadMap = new Button();
loadMap.setText("Load Map");
loadMap.setOnAction(e->{
	// @Override
	System.out.println("Load Map Button.");
    fileChooser.setTitle("Select a Maze");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("TXT", "*.txt")
    );
    File file = fileChooser.showOpenDialog(stage);

    if(file != null){
        // try{
            // desktop.open(file);
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
        }catch(NoRouteFoundException ex){
            System.out.println("Error: The given maze has no solution found, please check your maze.");
        }


        // }
        // catch(IOException ex){
        //     Logger.getLogger(Visual.class.getName()).log(Level.SEVERE,null,ex);
        // }
    }

});


Button loadRt = new Button();
loadRt.setText("Load Route");
loadRt.setOnAction(e->{
	// @Override
	System.out.println("Load Route Button.");
});

Button saveRt = new Button();
saveRt.setText("Save Route");
saveRt.setOnAction(e->{
	// @Override
	System.out.println("Save Route Button.");
});

Button step = new Button();
step.setText("Step");
step.setOnAction(e->{
	// @Override
	System.out.println("Step Button.");
});

// Circle c1 = new Circle (30); 

// c1.setFill (Color.YELLOW); 

// c1.setAlignment(Pos.TOP_RIGHT);
// c1.setCenterX(600);
// c1.setCenterY(0);
// ap.getChildren().add(c1); 
// ap.setLeftAnchor(c1, 10.0); 

// This is the roof (Triangle) 

// Polygon p1 = new Polygon (); 

// p1.getPoints().addAll(new Double[]{ 
// 150.0,50.0, 

// 80.0,200.0, 

// 220.0,200.0}); 

// p1.setFill (Color.RED); 


// This is the house (Rectangle) 

// Rectangle R1 = new Rectangle (140,130,140,130); 

// R1.setFill (Color.WHITE); 


// This implements the VBox for the whole window. 
// VBox root3 = new VBox();
// root3.setStyle("-fx-background-color:Blue");
// root3.setAlignment(Pos.TOP_RIGHT);
// root3.getChildren().addAll(c1);



// loadMaze();

// Rectangle R1 = new Rectangle (30,40,30,40); 

// R1.setArcWidth(15);
// R1.setArcHeight(15);
// // R1.setFill (Color.SLATEBLUE); 
// // R1.setStyle(" -fx-background-color: Green; -fx-border-radius:10;");
// // Rectangle R1 = new Rectangle (20,30,20,30); 
// R1.setFill(Color.PURPLE);

// Rectangle R2 = new Rectangle (20,30,20,30); 
// R2.setFill (Color.GREEN); 

VBox maze = new VBox(0); 
maze.setAlignment(Pos.CENTER); 
maze.getChildren().addAll(boxes);



VBox root = new VBox(0); 

// root.setBackground(1d2d2c); 
root.setStyle("-fx-background-color:White");

// root.setFill(Color.BLUE);
root.setAlignment(Pos.CENTER); 
root.getChildren().addAll(loadMap ,loadRt, saveRt, maze, step); 
// bp.setTop(root);

VBox root2 = new VBox();

root2.getChildren().addAll();
//bp.setLeft(leftVbox);
VBox group = new VBox();
group.getChildren().addAll(root,root2);
// bp.setRight(root);

// VBox subroot = new VBox(0);

// subroot.setBackground(Background.EMPTY);
// subroot.setAlignment(Pos.CENTER);
// subroot.getChildren().addAll(); 

// R1.setOnMouseClicked(e->{
// 	System.out.println("Clicked! "+ root.getStyle());
// 	if (root.getStyle()=="-fx-background-color:Blue"){
// 		root.setStyle("-fx-background-color:Black");
// 		root3.setStyle("-fx-background-color:Black");
// 		c1.setFill (Color.WHITE); 
// 	}else{
// 		root.setStyle("-fx-background-color:Blue");
// 		root3.setStyle("-fx-background-color:Blue");
// 		c1.setFill (Color.YELLOW); 

// 	}
// 	// 	if (status == 0){
// 	// 	System.out.println("0-1");
// 	// 	status = 1;
// 	// }else{
// 	// 	System.out.println("1-0");
// 	// 	status = 0;

// 	// }
	
// 	// R1.setOnMouseClicked(e1-> {System.out.println("SEC Clicked!");});
//   }
// );


// This is the scene 

Scene scene = new Scene(group); 
//,  600, 500, Color.GREEN

// Scene sceneb = new Scene(bp,  600, 500, Color.GREEN); 

scene.setOnKeyPressed(e->{
	// System.out.println(e.getText()+e.getCode()+c1.getRadius());
	// // c1.setRadius(c1.getRadius()+1.0); 
	// if(e.getCode().toString()=="ADD"){
	// 	c1.setRadius(c1.getRadius()+1.0); 

	// }else if(e.getCode().toString()=="SUBTRACT"){
	// 	c1.setRadius(c1.getRadius()-1.0);
	// }else{
	// 	System.out.println("Failed to grab the correct Key: "+e.getText());
	// }

});

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

            // // R1.setArcWidth(10);
            // Rectangle R2 = new Rectangle (20,30,20,30); 
            // R2.setFill(Color.GREEN);
            // R2.setArcHeight(15);
            // R2.setArcWidth(15);

            // many.add(R1);
            // many.add(R2);
            if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "#"){
                Rectangle R1 = new Rectangle (20,30,20,30); 
                R1.setFill (Color.SLATEBLUE); 
                R1.setArcHeight(15);
                R1.setArcWidth(15);
                many.add(R1);
            }else if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "."){
                Rectangle R1 = new Rectangle (20,30,20,30); 
                R1.setFill (Color.BLACK); 
                R1.setArcHeight(15);
                R1.setArcWidth(15);
                many.add(R1);           
            }else if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "x"){
                Rectangle R1 = new Rectangle (20,30,20,30); 
                R1.setFill (Color.GREEN); 
                R1.setArcHeight(15);
                R1.setArcWidth(15);
                many.add(R1);              
            }else if(test.getTileAtLocation(test.setCoord(i,ii)).toString() == "e"){
                Rectangle R1 = new Rectangle (20,30,20,30); 
                R1.setFill (Color.PURPLE); 
                R1.setArcHeight(15);
                R1.setArcWidth(15);
                many.add(R1);              
            }else{
                Rectangle R1 = new Rectangle (20,30,20,30); 
                R1.setFill (Color.RED); 
                R1.setArcHeight(15);
                R1.setArcWidth(15);
                many.add(R1);            
            }

        }
        HBox a = new HBox();
        a.setAlignment(Pos.CENTER);
        a.getChildren().addAll(many);
        many.clear();
        boxes.add(a);

    }
}

}
