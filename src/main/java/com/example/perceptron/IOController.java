package com.example.perceptron;

import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOController {

    public int numberOfInputs = 0;

    public List<Pair<List<Double>, String>> readFile(String filePath) {
        List<Pair<List<Double>, String>> data = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] lineSpilt = scanner.nextLine().split(",");
                List<Double> lineData = new ArrayList<>();
                numberOfInputs = lineSpilt.length - 1;
                int i;
                for (i = 0; i < lineSpilt.length - 1; i++) {
                    lineData.add(Double.valueOf(lineSpilt[i]));
                }
                data.add(new Pair<>(lineData, lineSpilt[i]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
