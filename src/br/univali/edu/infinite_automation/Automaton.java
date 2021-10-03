package br.univali.edu.infinite_automation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static javafx.application.Application.launch;

public class Automaton {
    private ArrayList<ArrayList<String>> automatonMatrix;
    private ArrayList<String> finalStates;
    private ArrayList<String> symbolsArray;

    public Automaton(ArrayList<ArrayList<String>> automatonMatrix, ArrayList<String> finalStates, ArrayList<String> symbolsArray) {
        this.automatonMatrix = automatonMatrix;
        this.finalStates = finalStates;
        this.symbolsArray = symbolsArray;
    }

    public ArrayList<ArrayList<String>> getAutomatonMatrix() {
        return automatonMatrix;
    }

    public void setAutomatonMatrix(ArrayList<ArrayList<String>> automatonMatrix) {
        this.automatonMatrix = automatonMatrix;
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<String> finalStates) {
        this.finalStates = finalStates;
    }

    public ArrayList<String> getSymbolsArray() {
        return symbolsArray;
    }

    public void setSymbolsArray(ArrayList<String> symbolsArray) {
        this.symbolsArray = symbolsArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automaton automaton = (Automaton) o;
        return Objects.equals(automatonMatrix, automaton.automatonMatrix) && Objects.equals(finalStates, automaton.finalStates) && Objects.equals(symbolsArray, automaton.symbolsArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(automatonMatrix, finalStates, symbolsArray);
    }

    private int getSymbolIndex(String symbol) {
        if (!this.symbolsArray.contains(symbol)) {
            return this.finalStates.size() - 1;
        }
        for (int i = 0; i < this.symbolsArray.size(); i++) {
            if (symbol.equals(this.symbolsArray.get(i))) {
                return i;
            }
        }

        return this.finalStates.size() - 1;
    }


    public String genericImplementation(ArrayList<String> sequence) {
        String state = "0";
        int actualIndex = 0;
        ArrayList<String> op = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%"));
        for(int i = 0; i < op.size(); i++){
            if(sequence.contains(op.get(i))){
                return "Operador aritmético: " + op.get(i);
            }
        }


        while (!Objects.equals(sequence.get(actualIndex), "$")) {
            state = this.automatonMatrix.get(Integer.parseInt(state)).get(getSymbolIndex(sequence.get(actualIndex)));
            actualIndex++;
        }

        if (Objects.equals(this.finalStates.get(Integer.parseInt(state)), "1")) {
            return "Sentença válida: " + convertSequenceToPrint(sequence);
        } else {
            return "ERRO: Sentença inválida: " + convertSequenceToPrint(sequence);
        }
    }


    public String convertSequenceToPrint(ArrayList<String> sequence) {
        String result = "";
        for (int i = 0; i < sequence.size() - 1; i++) {
            result += sequence.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
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

        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("a");
        sequence.add("a");
        sequence.add("a");
        sequence.add("x");
        sequence.add("b");
        sequence.add("c");
        sequence.add("$");


        System.out.println(automaton.genericImplementation(sequence));
    }
}
