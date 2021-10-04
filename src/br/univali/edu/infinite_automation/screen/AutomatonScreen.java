package br.univali.edu.infinite_automation.screen;

import br.univali.edu.infinite_automation.Automaton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AutomatonScreen {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="campA"
    private TextArea campA; // Value injected by FXMLLoader

    @FXML // fx:id="campB"
    private TextArea campB; // Value injected by FXMLLoader

    @FXML // fx:id="erase"
    private Button erase; // Value injected by FXMLLoader

    @FXML // fx:id="anchor"
    private AnchorPane anchor; // Value injected by FXMLLoader

    @FXML // fx:id="analyze"
    private Button analyze; // Value injected by FXMLLoader

    @FXML // fx:id="text"
    private Text text; // Value injected by FXMLLoader

    @FXML
    void onAnalyzeAction(ActionEvent event) {

    }

    @FXML
    void onAnalyzeClicked(MouseEvent event) {
        ArrayList<ArrayList<String>> automatonMatrix = new ArrayList<>();
        ArrayList<String> sequence1 = new ArrayList<>();
        sequence1.add("0");
        sequence1.add("1");
        sequence1.add("3");
        sequence1.add("3");
        automatonMatrix.add(sequence1);

        ArrayList<String> sequence2 = new ArrayList<>();
        sequence2.add("3");
        sequence2.add("1");
        sequence2.add("2");
        sequence2.add("3");
        automatonMatrix.add(sequence2);

        ArrayList<String> sequence3 = new ArrayList<>();
        sequence3.add("0");
        sequence3.add("3");
        sequence3.add("2");
        sequence3.add("3");
        automatonMatrix.add(sequence3);

        ArrayList<String> sequence4 = new ArrayList<>();
        sequence4.add("3");
        sequence4.add("3");
        sequence4.add("3");
        sequence4.add("3");
        automatonMatrix.add(sequence4);


        ArrayList<String> finalStates = new ArrayList<>();
        finalStates.add("0");
        finalStates.add("0");
        finalStates.add("1");
        finalStates.add("0");


        ArrayList<String> symbolsArray = new ArrayList<>();
        symbolsArray.add("a");
        symbolsArray.add("b");
        symbolsArray.add("c");

        Automaton automaton = new Automaton(automatonMatrix, finalStates, symbolsArray);

        this.campB.setText(getResult(automaton));
    }


    private String getResult(Automaton automaton) {
        ArrayList<ArrayList<String>> sequences = getSequences();
        String result = "";

        for (int i = 0; i < sequences.size(); i++) {
            result += automaton.genericImplementation(sequences.get(i)) + "\n";
        }

        return result;
    }

    private ArrayList<ArrayList<String>> getSequences() {
        String[] sequences = this.campA.getText().split(" ");

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> helper = new ArrayList<>();

        for (String sequence : sequences) {
            for (int j = 0; j < sequence.length(); j++) {
                helper.add(Character.toString(sequence.charAt(j)));
            }


            helper.add("$");
            result.add(helper);
            helper = new ArrayList<>();

        }
        System.out.println(result);
        return result;
    }



    @FXML
    void onEraseAction(ActionEvent event) {
        this.campA.setText("");
        this.campB.setText("");
    }

    @FXML
    void onEraseClicked(MouseEvent event) {

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert campA != null : "fx:id=\"campA\" was not injected: check your FXML file 'trab2.fxml'.";
        assert campB != null : "fx:id=\"campB\" was not injected: check your FXML file 'trab2.fxml'.";
        assert erase != null : "fx:id=\"erase\" was not injected: check your FXML file 'trab2.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'trab2.fxml'.";
        assert analyze != null : "fx:id=\"analyze\" was not injected: check your FXML file 'trab2.fxml'.";
        assert text != null : "fx:id=\"text\" was not injected: check your FXML file 'trab2.fxml'.";

    }

}
