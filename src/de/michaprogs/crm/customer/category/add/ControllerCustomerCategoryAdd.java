package de.michaprogs.crm.customer.category.add;

import de.michaprogs.crm.DeleteAlert;
import de.michaprogs.crm.Main;
import de.michaprogs.crm.customer.category.DeleteCustomerCategory;
import de.michaprogs.crm.customer.category.InsertCustomerCategory;
import de.michaprogs.crm.customer.category.ModelCustomerCategory;
import de.michaprogs.crm.customer.category.SelectCustomerCategory;
import de.michaprogs.crm.customer.category.UpdateCustomerCategory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class ControllerCustomerCategoryAdd {

@FXML private Label lblSubHeadline;
	
	@FXML private TableView<ModelCustomerCategory> tvCustomerCategory;
	@FXML private TableColumn<ModelCustomerCategory, Integer> tcCustomerCategoryID;
	@FXML private TableColumn<ModelCustomerCategory, String> tcCustomerCategory;
	
	@FXML private TextField tfCustomerCategory;
	
	@FXML private Button btnAdd;
	@FXML private Button btnDelete;
	
	private Stage stage;
	private Main main;
	
	public ControllerCustomerCategoryAdd(){
		
	}
	
	@FXML private void initialize(){
		
		/* BUTTONS */
		initBtnAdd();
		initBtnDelete();
		
		/* TABLES */
		initTableCustomerCategory();
		
		refresh();
		
	}
	
	/*
	 * BUTTONS
	 */
	private void initBtnAdd(){
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				if(! tfCustomerCategory.getText().isEmpty()){					
					new InsertCustomerCategory(new ModelCustomerCategory(tfCustomerCategory.getText()));
					refresh();
					tfCustomerCategory.clear();
				}else{
					System.out.println("Bitte Bezeichnung angeben!");
				}
				
			}
		});
		
	}
	
	private void initBtnDelete(){
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deleteCustomerCategory();
			}
		});
		
	}
	
	/*
	 * TABLE
	 */
	private void initTableCustomerCategory(){
		
		tcCustomerCategoryID.setCellValueFactory(new PropertyValueFactory<>("CustomerCategoryID"));
		tcCustomerCategory.setCellValueFactory(new PropertyValueFactory<>("CustomerCategory"));
		tcCustomerCategory.setCellFactory(TextFieldTableCell.forTableColumn());
		tcCustomerCategory.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ModelCustomerCategory,String>>() {

			@Override
			public void handle(CellEditEvent<ModelCustomerCategory, String> event) {
				((ModelCustomerCategory)event.getTableView().getItems().get(
					event.getTablePosition().getRow())).setCustomerCategory(event.getNewValue());
				
				new UpdateCustomerCategory(new ModelCustomerCategory(	tcCustomerCategoryID.getCellData(event.getTablePosition().getRow()), 
																	tcCustomerCategory.getCellData(event.getTablePosition().getRow())));
								
			}
			
		});

		tvCustomerCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModelCustomerCategory>() {

			@Override
			public void changed(ObservableValue<? extends ModelCustomerCategory> observable,
					ModelCustomerCategory oldValue, ModelCustomerCategory newValue) {
				lblSubHeadline.setText(tcCustomerCategory.getCellData(tvCustomerCategory.getSelectionModel().getSelectedIndex()));				
			}
			
		});
		
		tvCustomerCategory.setContextMenu(new ContextMenuTableCategory());
		
	}
	
	/*
	 * DATABASE METHODS
	 */
	private void refresh(){
		tvCustomerCategory.setItems(new SelectCustomerCategory(new ModelCustomerCategory()).getModelCustomerCategory().getObsListCustomerCategories());
	}
	
	private void deleteCustomerCategory(){
		
		if(tvCustomerCategory.getSelectionModel().getSelectedItems().size() == 1){
			
			DeleteAlert delete = new DeleteAlert();
			if(delete.getDelete()){
				System.out.println(tcCustomerCategoryID.getCellData(tvCustomerCategory.getSelectionModel().getSelectedIndex()));
				new DeleteCustomerCategory(
					new ModelCustomerCategory(
						tcCustomerCategoryID.getCellData(tvCustomerCategory.getSelectionModel().getSelectedIndex()),
						tcCustomerCategory.getCellData(tvCustomerCategory.getSelectionModel().getFocusedIndex())
					)
				);
			}
			
			refresh();
			
		}else{
			System.out.println("Bitte 1 Zeile markieren!");
		}
		
	}
	
	/*
	 * GETTER & SETTER
	 */
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	private class ContextMenuTableCategory extends ContextMenu{
		
		private MenuItem itemDelete = new MenuItem("Löschen");
		
		public ContextMenuTableCategory(){
			
			initItemDelete();
			
			this.getItems().add(itemDelete);
			
		}
		
		/* MENU ITEMS */
		private void initItemDelete(){
			
			itemDelete.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					deleteCustomerCategory();
				}
			});
			
		}
		
	}
	
}
