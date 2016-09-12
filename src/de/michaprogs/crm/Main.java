package de.michaprogs.crm;
	
import de.michaprogs.crm.customer.data.LoadCustomerData;
import de.michaprogs.crm.database.CreateTables;
import de.michaprogs.crm.menubar.LoadMenuBar;
import de.michaprogs.crm.navigation.LoadNavigation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage stage;
	private BorderPane contentPane = new BorderPane();
	private String programName = "Fluid CRM";
	
	@Override
	public void start(Stage stage) {

		try {
			
			this.stage = stage;
			
			new CreateTables();
			
			LoadMenuBar menubar = new LoadMenuBar(false, this);			
			LoadNavigation navigation = new LoadNavigation(this);			
			LoadCustomerData customerData = new LoadCustomerData(false, this);	
			
			contentPane.setTop(menubar.getContent());
			contentPane.setLeft(navigation.getContent());			
			contentPane.setCenter(customerData.getContent());
			
			Scene scene = new Scene(contentPane);
			scene.getStylesheets().add("style.css");
			
			stage.setScene(scene);
			stage.setTitle(programName);
			stage.getIcons().add(new Image("file:resources/app_icon.png"));
			stage.setWidth(1200);
			stage.setHeight(600);
			stage.setMaximized(true);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public BorderPane getContentPane(){
		return contentPane;
	}
	
	public String getProgramName(){
		return programName;
	}

}
