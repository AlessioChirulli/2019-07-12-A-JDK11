/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Congiunti;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	try {
    	int porzioni=Integer.parseInt(txtPorzioni.getText());
    	txtResult.setText(model.creaGrafo(porzioni));
    	boxFood.getItems().addAll(model.getVertex());
    	}catch(NumberFormatException nbe) {
    		txtResult.setText("Inserisci un numero valido!");
    	}
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	txtResult.clear();
    	Food f=boxFood.getValue();
    	if(f!=null) {
    		List<Congiunti> result=model.getCongiunti(f,5);
    		txtResult.appendText("LISTA CALORIE CONGIUNTE:\n");
    		for(Congiunti c:result) {
    			txtResult.appendText(c.getF()+" - "+c.getPeso()+"\n");
    		}
    	}else {
    		txtResult.setText("Inserisci un cibo valido");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	try {
        	int k=Integer.parseInt(txtK.getText());
        	Food f=boxFood.getValue();
        	if(f!=null) {
        	txtResult.setText(model.simula(k, f));
        	}else {
        		txtResult.setText("Inserisci un cibo valido");
        	}
        	}catch(NumberFormatException nbe) {
        		txtResult.setText("Inserisci un numero valido!");
        	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
