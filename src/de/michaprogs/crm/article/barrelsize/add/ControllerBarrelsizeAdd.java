package de.michaprogs.crm.article.barrelsize.add;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.article.barrelsize.ModelBarrelsize;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerBarrelsizeAdd {

	//Tables & Columns
	@FXML private TableView<ModelBarrelsize> tvBarrelsize;
	@FXML private TableColumn<ModelBarrelsize, Integer> tcBarrelsizeID;
	@FXML private TableColumn<ModelBarrelsize, String> tcBarrelsize;
	
	@FXML private Label lblSubHeadline;
	
	//TextFields
	@FXML private TextField tfBarrelsize;
	
	//Buttons
	@FXML private Button btnAdd;
	@FXML private Button btnAbort;
	
	private Stage stage;
	
	public ControllerBarrelsizeAdd(){}
	
	@FXML private void initialize(){
		
		this.tcBarrelsizeID.setCellValueFactory(new PropertyValueFactory<>("barrelsizeID"));
		this.tcBarrelsize.setCellValueFactory(new PropertyValueFactory<>("barrelsize"));		
		
		initTableDoubleClick();
		
		//Init Buttons
		initBtnAbort();
		initBtnAdd();
		
		//Load all barrelsize from Database and show
		refreshTableBarrelsize();
		
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void initBtnAbort(){
		
		btnAbort.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(stage != null){
					stage.close();
				}else{
					tfBarrelsize.clear();
				}
			}
		});	
	}
	
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				new ModelBarrelsize().insertbarrelsize(tfBarrelsize.getText());
				refreshTableBarrelsize();
				tfBarrelsize.clear();
				
			}
		});
		
	}
	
	private void initTableDoubleClick(){
		
		tvBarrelsize.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(	event.getButton().equals(MouseButton.SECONDARY) &&
					event.getClickCount() == 1){
					tvBarrelsize.setContextMenu(new ContextMenuTable());
				}
				
				if(tvBarrelsize.getSelectionModel().getSelectedItems().size() == 1){
					lblSubHeadline.setText(tcBarrelsize.getCellData(tvBarrelsize.getSelectionModel().getSelectedIndex()));
				}
					
				
			}
		});
		
	}
	
	private void refreshTableBarrelsize(){		
		ModelBarrelsize barrelsize = new ModelBarrelsize();
		barrelsize.selectBarrelsizes();
		tvBarrelsize.setItems(barrelsize.getObsListBarrelsizes());	
	}
	
	private class ContextMenuTable extends ContextMenu{
		
		private MenuItem itemDelete;
		
		public ContextMenuTable(){
			
			initItemDelete();
			
			this.getItems().add(itemDelete);
			
		}
		
		private void initItemDelete(){
			
			itemDelete = new MenuItem("Löschen");
			itemDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					DeleteAlert delete = new DeleteAlert();
					if(delete.getDelete()){
						ModelBarrelsize barrelsize = new ModelBarrelsize();
						barrelsize.deleteBarrelsize(tcBarrelsizeID.getCellData(tvBarrelsize.getSelectionModel().getSelectedIndex()), 
													tcBarrelsize.getCellData(tvBarrelsize.getSelectionModel().getSelectedIndex()));
					
						refreshTableBarrelsize();
					}
					
				}
			});
			
		}
		
	}
	
}
