package de.michaprogs.crm.navigation;

import de.michaprogs.crm.GraphicButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerNavigation {
	
	@FXML private AnchorPane anchorpaneSidebar;
	
	@FXML private Button btnCollapse;
	
	@FXML private Button btnArticle;
	@FXML private Button btnSupplier;
	@FXML private Button btnOffer;
	@FXML private Button btnStock;
	
	private BorderPane content;
	
	public ControllerNavigation(){}
	
	@FXML private void initialize(){		
		
		initBtnCollapse();
		
	}
	
	/**
	 *  set by main-class 
	 *	@param content - the contentpane (borderpane) where the panels are changed 
	 */	
	public void setContent(BorderPane content){
		this.content = content;
	}
	
	private void initBtnCollapse(){
		
		btnCollapse.setGraphic(new GraphicButton("file:img/menu_collapse_32.png").getGraphicButton());
		btnCollapse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(anchorpaneSidebar.getPrefWidth() != 50){
					btnArticle.setText("");
					btnSupplier.setText("");
					btnOffer.setText("");
					btnStock.setText("");
					anchorpaneSidebar.setPrefWidth(50);
				}else{
					/* SAME TEXT AS IN THE FXML FILE */
					btnArticle.setText("Artikel");
					btnSupplier.setText("Lieferanten");
					btnOffer.setText("Angebot");
					btnStock.setText("Lager");
					/* SAME WIDTH AS IN THE FXML FILE */
					anchorpaneSidebar.setPrefWidth(200);
				}
				
				
			}
		});
		
	}
	
}
