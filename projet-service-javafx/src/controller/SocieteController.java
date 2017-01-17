package controller;

import java.rmi.Naming;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import SocieteApp;
import model.OrdreModel;
import model.SocieteModel;
import org.sid.rmi.ISocieteRemote;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SocieteController {
	
	private SocieteApp scApp;
	private ISocieteRemote stub;
	
	@FXML
    private TableView<SocieteModel> tableSocietes;
	@FXML
    private TableColumn<SocieteModel, String> colCode;
	@FXML
    private TableColumn<SocieteModel, String> colNom;

	@FXML
    private Label txtNom;
    @FXML
    private Label txtPrixAchat;
    @FXML
    private Label txtPrixEstm;
    @FXML
    private Label txtCode;
    @FXML
    private Label txtTotalAchat;
    @FXML
    private Label txtTotalVente;
    @FXML
    private Label txtPrixVente;

    @FXML
    private Button btnOrdres;
    
    @FXML
    void showOrdres(ActionEvent event) throws Exception {
    	ObservableList<OrdreModel> data=FXCollections.observableArrayList();
    	System.out.println(txtCode.getText());
        SocieteModel selectedSc = tableSocietes.getSelectionModel().getSelectedItem();
        List<org.sid.entities.Ordre> ords=this.stub.listeVentes(selectedSc.getCode());
		for(org.sid.entities.Ordre o:ords){
			data.add(new OrdreModel(o.getNum(),o.getPrixAction(),o.getDate(),o.getNbreAction()));
		}
        if (data != null) {
        	scApp.showOrdresDialog(data,selectedSc.getNom());
        }
        else{
        	System.out.println(" Error: No Ordres found !!");
        }
    }

    public SocieteController() throws Exception {
    	this.stub=(ISocieteRemote) Naming.lookup("rmi://localhost:1099/SC");
    }
    
    @FXML
    private void initialize() {
    	
        // Initialize the societe table with the two columns.
        colCode.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty());
        colNom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        
        // Clear societe details.
        showSocieteDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableSocietes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSocieteDetails(newValue));
        
    }

	public void setScApp(SocieteApp scApp) {
		this.scApp = scApp;
		tableSocietes.setItems(scApp.getSocieteData());
	}
	
	private void showSocieteDetails(SocieteModel sc) {
		Double prixAchat = null,prixVente = null,estim = null;
		Long totalAchat = null,totalVente = null;
	    if (sc != null) {
	        txtCode.setText(sc.getCode());
	        txtNom.setText(sc.getNom());
	        
			try {
				totalAchat=this.stub.totalAchats(sc.getCode());
		        totalVente=this.stub.totalVentes(sc.getCode());
		        prixAchat=this.stub.avgPrixAchats(sc.getCode());
		        prixVente=this.stub.avgPrixVentes(sc.getCode());
		        estim=this.stub.estimation(sc.getCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(totalAchat!=null && totalVente!=null){
				txtTotalAchat.setText(Long.toString(totalAchat));
				txtPrixVente.setText(Double.toString(prixVente));
		        txtTotalVente.setText(Double.toString(totalVente));
		        txtPrixAchat.setText(Double.toString(prixAchat));
		        txtPrixEstm.setText(Double.toString(estim));
			}else{
				txtTotalAchat.setText("non trouve");
		        txtPrixVente.setText("non trouve");
		        txtTotalVente.setText("non trouve");
		        txtPrixAchat.setText("non trouve");
		        txtPrixEstm.setText("non trouve");
			}
			
	        
	    } else {
	    	txtCode.setText("");
	        txtNom.setText("");
	        txtTotalAchat.setText("");
	        txtPrixVente.setText("");
	        txtTotalVente.setText("");
	        txtPrixAchat.setText("");
	        txtPrixEstm.setText("");
	    }
	}
       
}


