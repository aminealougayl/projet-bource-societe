package controller;

import java.util.Date;

import model.OrdreModel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OrdreDialogController {

	private Stage dialogStage;
	
	@FXML
    private TableView<OrdreModel> tableOrdres;
    @FXML
    private TableColumn<OrdreModel, Long> colNum;
    @FXML
    private TableColumn<OrdreModel, Date> colDate;
    @FXML
    private TableColumn<OrdreModel, Double> colPrix;
    @FXML
    private TableColumn<OrdreModel, Integer> colNbre;
    
    @FXML
    private Label txtTypeOrdres;
    @FXML
    private Label txtNomSociete;

    @FXML
    private void initialize() {
    	colNum.setCellValueFactory(cellData -> cellData.getValue().getNumProperty().asObject());
    	colDate.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
    	colPrix.setCellValueFactory(cellData -> cellData.getValue().getPrixProperty().asObject());
    	colNbre.setCellValueFactory(cellData -> cellData.getValue().getNbreProperty().asObject());
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setTableOrdres(ObservableList<OrdreModel> data) {
		tableOrdres.setItems(data);
	}
    
    public void setNomSc(String nom) {
    	txtNomSociete.setText(nom);
	}
    

}
