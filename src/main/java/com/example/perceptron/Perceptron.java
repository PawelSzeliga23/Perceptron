package com.example.perceptron;

import javafx.util.Pair;

import java.util.*;

public class Perceptron {
    private double[] weights;
    private double learningRate;
    private int epochs;
    private double step;
    private Map<String, Integer> classes;
    private List<Pair<Integer, Double>> accPerEpoch;

    public Perceptron(int inputsCount, double learningRate, int epochs, Map<String, Integer> classes) {
        this.learningRate = learningRate;
        weights = new double[inputsCount];
        this.epochs = epochs;
        this.classes = classes;
        this.step = 0.01;
        accPerEpoch = new ArrayList<>();
        for (int i = 0; i < inputsCount; i++) {
            weights[i] = Math.random();
        }
    }

    public void trainAndTest(List<Pair<List<Double>, String>> trainingData, List<Pair<List<Double>, String>> testData) {
        for (int i = 0; i < epochs; i++) {
            Collections.shuffle(trainingData);
            train(trainingData);
            accPerEpoch.add(test(testData, i));
        }
    }

    private int stepFunction(double sum) {
        return sum > step ? 1 : 0;
    }

    private Pair<Integer, Double> test(List<Pair<List<Double>, String>> testData, int epoch) {
        double counter = 0;
        for (var pair :
                testData) {
            List<Double> vector = pair.getKey();
            String target = pair.getValue();
            int output = guess(vector);
            int error = classes.get(target) - output;
            if (error == 0)
                counter++;
        }
        return new Pair<>(epoch + 1, ((counter / (double) testData.size()) * 100));
    }

    private void train(List<Pair<List<Double>, String>> trainingData) {
        for (var pair : trainingData) {
            List<Double> vector = pair.getKey();
            String target = pair.getValue();
            int output = guess(vector);
            double error = classes.get(target) - output;
            for (int i = 0; i < weights.length; i++) {
                weights[i] += learningRate * error * vector.get(i);
            }
            step += error * learningRate * (-1);
        }
    }

    private int guess(List<Double> vector) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * vector.get(i);
        }
        return stepFunction(sum);
    }

    public List<Pair<Integer, Double>> getAccPerEpoch() {
        return accPerEpoch;
    }

    public String predictData(List<Double> data) {
        int output = guess(data);
        for (var entry : classes.entrySet()) {
            if (entry.getValue().equals(output)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
