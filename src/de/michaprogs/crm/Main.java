package de.michaprogs.crm;
	
import de.michaprogs.crm.navigation.LoadNavigation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {

		try {
			
			LoadNavigation navigation = new LoadNavigation();
			
			
			BorderPane root = new BorderPane();
			root.setLeft(navigation.getContent());			
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add("style.css");
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("CRM");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public static void main(String[] args) {
		launch(args);
	}

}
