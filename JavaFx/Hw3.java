package Hw3_HenShiryon;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;
/*
 * Hen Shiryon 
 * 2098469718
*/
public class Hw3 extends Application {

	// define all the parameters outside the methods
	int count = 0;
	int time = 0;
	Text text= new Text("tickcount:" + count +    "   tickinterval:" + (time+1000));
	Button undo=new Button("Undo");
	Button redo=new Button("Redo");
	HBox b1;
	double textlocation = 0;
	Timeline show;
	BorderPane bp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// creating hbox that contains the two buttons 
		// TODO Auto-generated method stub
		
		b1 = new HBox(redo,undo);
		b1.setPadding(new Insets(20));
		
		 show = new Timeline(new KeyFrame(Duration.millis(100), e2 -> {
				TextMovement();
			}));
		show.setCycleCount(show.INDEFINITE);
		bp=new BorderPane();
		bp.setTop(b1);
		
		Scene scene= new Scene(bp, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setResizable(false);
		primaryStage.setTitle("DisplayMovingMessage");
		primaryStage.show();
		Pane p1=new Pane(text);
		bp.setCenter(p1);
		undo.setOnAction(e -> UndoButton());
		b1.requestFocus();
		redo.setOnAction(e -> RedoButton());
		b1.requestFocus();
		// activating the movement by clicking s or on the mouse 
		bp.setOnKeyPressed(e-> {
	        	if (e.getCode()==KeyCode.S) {
					show.play();
				}
			});
		   bp.setOnMouseClicked(e->{
			   show.play();
		   });
		  
		

	}
     // define the text movement 
	private void TextMovement() {
		count++;
		time++;
		if (text.getX()>bp.getWidth()) {
			
			text.setX(-250);
		} else {
			text.setX(text.getX() + 5);
		}
		text.setText("tickcount:" + count + "   tickinterval:" + (time+1000));

	}
// the method of the undo button
	private Object UndoButton() {
		textlocation = text.getX();
		text.setX(0);
		count = 0;
		time = 0;
		text.setText("tickcount:" + count +    "   tickinterval:" + (time+1000));
		show.stop();
		return null;

	}
	// the method of the redo button
	private Object RedoButton() {
		text.setX(textlocation);
		time = (int)(textlocation / 5);
		count = time;
		text.setText("tickcount:" + count +    "   tickinterval:" + (time+1000));

		return null;

	}
	
}
