package com.example.perceptron;

import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.util.*;

public class PerceptronController {
    private Perceptron perceptron;

    public PerceptronController(String testPath, String trainPath, int epoch, double learningRate) {
        IOController controller = new IOController();
        var trainList = controller.readFile(trainPath);
        int inputs = controller.numberOfInputs;
        var testList = controller.readFile(testPath);
        perceptron = new Perceptron(inputs, learningRate, epoch, prepareClasses(trainList));
        perceptron.trainAndTest(trainList, testList);
    }

    private Map<String, Integer> prepareClasses(List<Pair<List<Double>, String>> data) {
        int count = 0;
        Map<String, Integer> classes = new HashMap<>();
        Set<String> cl = new HashSet<>();
        for (var pair : data) {
            cl.add(pair.getValue());
        }
        for (var c : cl) {
            classes.put(c, count++);
        }
        return classes;
    }

    public XYChart.Series<Integer, Double> getChartData() {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();
        var list = perceptron.getAccPerEpoch();
        for (var pair :
                list) {
            series.getData().add(new XYChart.Data<>(pair.getKey(), pair.getValue()));
        }
        series.setName("Epoch Accuracy");
        return series;
    }

    public String predictData(String line) {
        return perceptron.predictData(prepareData(line));
    }
    private List<Double> prepareData(String line){
        List<Double> result = new ArrayList<>();
        String[] parts = line.split(",");
        for (int i = 0; i < parts.length; i++) {
            result.add(Double.parseDouble(parts[i]));
        }
        return result;
    }

}
