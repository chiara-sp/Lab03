/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dizionario;
	private List<String> inputTextList;

	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<String> boxLingua;

	    @FXML
	    private TextArea txtTesto;

	    @FXML
	    private Button btnSpell;

	    @FXML
	    private TextArea txtErrori;

	    @FXML
	    private Button btnclear;

	    @FXML
	    private Label txtStato;

	    @FXML
	    void doclear(ActionEvent event) {

	    	txtTesto.clear();
	    	txtErrori.clear();
	    }

	    @FXML
	    void dospell(ActionEvent event) {

	    	//txtTesto.clear();
	    	inputTextList= new LinkedList<String>();
	    	
	    	if(boxLingua.getValue()== null) {
	    		txtTesto.setText("Selezionare la lingua");
	    		return;
	    	}
	    	if(!dizionario.loadDictionary(boxLingua.getValue())) {
	    		txtTesto.setText("Errore nel caricamento del dizionario");
	    		return;
	    	}
	    	String daCorreggere = txtTesto.getText();
	    	System.out.println("testo: "+daCorreggere);
	    	
	    	if(daCorreggere.isEmpty()) {
	    		txtTesto.setText("inserire testo da correggere!");
	    		return;
	    	}
	    	StringTokenizer st = new StringTokenizer(daCorreggere, " ");
	    	while(st.hasMoreTokens()) {
	    		inputTextList.add(st.nextToken());
	    	}
	    	List<RichWord> corrette= dizionario.spellCheckText(inputTextList);
	    	
	    	for(RichWord r: corrette) {
	    		if(!r.isCorretto()) {
	    			txtErrori.appendText(r.getParola()+ "\n");
	    		}
	    	}
	    	
	    }

	    @FXML
	    void initialize() {
	        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert btnclear != null : "fx:id=\"btnclear\" was not injected: check your FXML file 'Scene.fxml'.";
	        assert txtStato != null : "fx:id=\"txtStato\" was not injected: check your FXML file 'Scene.fxml'.";

	    }
public void setModel(Dictionary model) {
    	
    	
    	/*txtTesto.setText("Selezionare una lingua");
    	
    	 * txtTesto.setDisable(true);
    	txtErrori.setDisable(true);*/
    	boxLingua.getItems().addAll("English","Italian");
    	
    	
 	
    	
    	this.dizionario = model;
    }
}


