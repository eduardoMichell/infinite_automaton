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
        ArrayList<String> symbolsArray = new ArrayList<>();
        symbolsArray.add("a");
        symbolsArray.add("b");
        symbolsArray.add("c");
        symbolsArray.add("d");
        symbolsArray.add("e");


        ArrayList<String> op = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%"));
        for (String s : op) {
            if (sequence.contains(s)) {
                return "Operador aritmético: " + s;
            }
        }
        boolean invalidSymbol = false;
        for (int i = 0; i < sequence.size() - 1; i++) {
            if (!symbolsArray.contains(sequence.get(i))) {
                invalidSymbol = true;
                break;
            }
        }

        if (invalidSymbol) {
            return "ERRO: Símbolo(s) inválido(s): " + convertSequenceToPrint(sequence);
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
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sequence.size() - 1; i++) {
            result.append(sequence.get(i));
        }
        return result.toString();
    }

}
