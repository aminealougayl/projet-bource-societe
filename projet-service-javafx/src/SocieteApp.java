import java.io.IOException;
import java.rmi.Naming;
import java.util.List;

import controller.OrdreDialogController;
import controller.SocieteController;
import org.sid.entities.Societe;
import model.OrdreModel;
import model.SocieteModel;
import org.sid.rmi.ISocieteRemote;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SocieteApp extends Application{
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private ISocieteRemote stub;
    private ObservableList<SocieteModel> societeData = FXCollections.observableArrayList();
    
    public static void main(String[] args) {
		launch(args);
	} 
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Societe App");

        showSocieteview();
		
	}

	private void showSocieteview() throws IOException {
		
		// Load societe layout from fxml file.
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SocieteApp.class.getResource("view/SocieteView.fxml"));
        rootLayout = (BorderPane) loader.load();
        
        //Get Controller
        SocieteController ctrl=loader.getController();
        ctrl.setScApp(this);
        
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public SocieteApp() throws Exception{
		stub=(ISocieteRemote) Naming.lookup("rmi://localhost:1099/SC");
    	List<Societe> societes= stub.listeSocietes();
    	for(org.sid.entities.Societe s:societes){
    		societeData.add(new SocieteModel(s.getCode(), s.getNom()));
    	}
	}

	public ObservableList<SocieteModel> getSocieteData() {
		return societeData;
	}

	public void showOrdresDialog(ObservableList<OrdreModel> data,String nom) throws IOException{
		
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SocieteApp.class.getResource("view/OrdreDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ordres Societe");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the data and the name into the controller.
        OrdreDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTableOrdres(data);
        controller.setNomSc(nom);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
	}
	
}
