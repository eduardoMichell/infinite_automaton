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
import java.util.*;

public class AutomatonScreen {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    private final ArrayList<Character> op = new ArrayList<>(Arrays.asList('+', '-', '*', '/', '%'));

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
        ArrayList<String> sequence0 = new ArrayList<>();
        sequence0.add("1");
        sequence0.add("13");
        sequence0.add("2");
        sequence0.add("3");
        sequence0.add("13");
        automatonMatrix.add(sequence0);

        ArrayList<String> sequence1 = new ArrayList<>();
        sequence1.add("13");
        sequence1.add("4");
        sequence1.add("13");
        sequence1.add("13");
        sequence1.add("13");
        automatonMatrix.add(sequence1);

        ArrayList<String> sequence2 = new ArrayList<>();
        sequence2.add("13");
        sequence2.add("13");
        sequence2.add("5");
        sequence2.add("6");
        sequence2.add("13");
        automatonMatrix.add(sequence2);

        ArrayList<String> sequence3 = new ArrayList<>();
        sequence3.add("13");
        sequence3.add("13");
        sequence3.add("13");
        sequence3.add("13");
        sequence3.add("7");
        automatonMatrix.add(sequence3);

        ArrayList<String> sequence4 = new ArrayList<>();
        sequence4.add("8");
        sequence4.add("13");
        sequence4.add("13");
        sequence4.add("13");
        sequence4.add("13");
        automatonMatrix.add(sequence4);

        ArrayList<String> sequence5 = new ArrayList<>();
        sequence5.add("13");
        sequence5.add("13");
        sequence5.add("2");
        sequence5.add("3");
        sequence5.add("13");
        automatonMatrix.add(sequence5);

        ArrayList<String> sequence6 = new ArrayList<>();
        sequence6.add("13");
        sequence6.add("13");
        sequence6.add("13");
        sequence6.add("13");
        sequence6.add("9");
        automatonMatrix.add(sequence6);

        ArrayList<String> sequence7 = new ArrayList<>();
        sequence7.add("13");
        sequence7.add("13");
        sequence7.add("13");
        sequence7.add("6");
        sequence7.add("13");
        automatonMatrix.add(sequence7);

        ArrayList<String> sequence8 = new ArrayList<>();
        sequence8.add("13");
        sequence8.add("10");
        sequence8.add("13");
        sequence8.add("13");
        sequence8.add("13");
        automatonMatrix.add(sequence8);

        ArrayList<String> sequence9 = new ArrayList<>();
        sequence9.add("13");
        sequence9.add("13");
        sequence9.add("13");
        sequence9.add("3");
        sequence9.add("13");
        automatonMatrix.add(sequence9);

        ArrayList<String> sequence10 = new ArrayList<>();
        sequence10.add("1");
        sequence10.add("13");
        sequence10.add("11");
        sequence10.add("3");
        sequence10.add("13");
        automatonMatrix.add(sequence10);

        ArrayList<String> sequence11 = new ArrayList<>();
        sequence11.add("13");
        sequence11.add("13");
        sequence11.add("12");
        sequence11.add("6");
        sequence11.add("13");
        automatonMatrix.add(sequence11);

        ArrayList<String> sequence12 = new ArrayList<>();
        sequence12.add("13");
        sequence12.add("13");
        sequence12.add("11");
        sequence12.add("13");
        sequence12.add("13");
        automatonMatrix.add(sequence12);

        ArrayList<String> sequence13 = new ArrayList<>();
        sequence13.add("13");
        sequence13.add("13");
        sequence13.add("13");
        sequence13.add("13");
        sequence13.add("13");
        automatonMatrix.add(sequence13);

        ArrayList<String> finalStates = new ArrayList<>();
        finalStates.add("0"); // e0
        finalStates.add("0"); // e1
        finalStates.add("1"); // e2
        finalStates.add("0"); // e3
        finalStates.add("0"); // e4
        finalStates.add("0"); // e5
        finalStates.add("0"); // e6
        finalStates.add("1"); // e7
        finalStates.add("0"); // e8
        finalStates.add("0"); // e9
        finalStates.add("0"); // e10
        finalStates.add("1"); // e11
        finalStates.add("0"); // e12
        finalStates.add("0"); // erro

        ArrayList<String> symbolsArray = new ArrayList<>();
        symbolsArray.add("a");
        symbolsArray.add("b");
        symbolsArray.add("c");
        symbolsArray.add("d");
        symbolsArray.add("e");

        Automaton automaton = new Automaton(automatonMatrix, finalStates, symbolsArray);

        this.campB.setText(getResult(automaton));
    }

    private String getResult(Automaton automaton) {
        ArrayList<ArrayList<String>> sequences = getSequences();
        StringBuilder result = new StringBuilder();
        for (ArrayList<String> sequence : sequences) {
            result.append(automaton.genericImplementation(sequence)).append("\n");
        }
        return result.toString();
    }

    private ArrayList<ArrayList<String>> getSequences() {
        String[] sequences = this.campA.getText().split(" ");

        ArrayList<String> sequencesNoSpace = new ArrayList<>();

        for (String s : sequences) {
            if (!s.isEmpty()) {
                sequencesNoSpace.add(s.trim());
            }
        }
        sequencesNoSpace = checkArithmeticOperators(sequencesNoSpace);
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> helper = new ArrayList<>();

        for (String sequence : sequencesNoSpace) {
            for (int j = 0; j < sequence.length(); j++) {
                helper.add(Character.toString(sequence.charAt(j)));
            }
            helper.add("$");
            result.add(helper);
            helper = new ArrayList<>();
        }
        return result;
    }

    private ArrayList<String> checkArithmeticOperators(ArrayList<String> sequences) {
        ArrayList<String> result = new ArrayList<>();

        for (String sequence : sequences) {
            StringBuilder currentSequence = new StringBuilder();
            if (checkIfHaveOp(sequence)) {
                for (int i = 0; i < sequence.length(); i++) {
                    boolean HaveOp = false;
                    if (this.op.contains(sequence.charAt(i))) {
                        if (currentSequence.length() > 0) {
                            result.add(currentSequence.toString());
                        }
                        currentSequence = new StringBuilder("" + sequence.charAt(i));
                        result.add(currentSequence.toString());
                        currentSequence = new StringBuilder();
                        HaveOp = true;
                    }
                    if (!HaveOp) {
                        currentSequence.append(sequence.charAt(i));
                        HaveOp = true;
                    }
                }
                if (currentSequence.length() > 0) {
                    result.add(currentSequence.toString());
                }
            } else {
                result.add(sequence);
            }
        }
        return result;
    }

    private boolean checkIfHaveOp(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (this.op.contains(string.charAt(i))) {
                return true;
            }
        }
        return false;
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
