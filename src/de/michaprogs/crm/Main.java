package de.michaprogs.crm;
	
import de.michaprogs.crm.article.data.LoadArticleData;
import de.michaprogs.crm.database.CreateTables;
import de.michaprogs.crm.menubar.LoadMenuBar;
import de.michaprogs.crm.navigation.LoadNavigation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {

		try {
			
			new CreateTables();
			BorderPane root = new BorderPane();
			
			LoadMenuBar menubar = new LoadMenuBar(false);
			LoadNavigation navigation = new LoadNavigation();
			LoadArticleData articleData = new LoadArticleData();
			
			navigation.getController().setContent(root);
			menubar.getController().setContent(root);
			
			root.setTop(menubar.getContent());
			root.setLeft(navigation.getContent());			
			root.setCenter(articleData.getContent());
			
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
