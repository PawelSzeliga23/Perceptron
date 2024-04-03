package com.example.perceptron;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgramPageController implements Initializable {
    public Button predictButton;
    public TextField inputLabel;
    public Label predictLabel;
    public LineChart<Integer, Double> epochChart;
    static PerceptronController perceptronController;

    public void setPerceptronController(PerceptronController perceptronController) {
        ProgramPageController.perceptronController = perceptronController;
    }

    public void load(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("program-page.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        epochChart.getData().add(perceptronController.getChartData());
        for (var data : epochChart.getData().get(0).getData()) {
            Tooltip.install(data.getNode(), new Tooltip("Epoch: " + data.getXValue() + ", Accuracy: " + data.getYValue()));
        }
    }
    public void predictData(ActionEvent event){
        if(inputLabel.getText().equals("")){
            return;
        }
        String line = inputLabel.getText();
        predictLabel.setText("Perceptron output: " + perceptronController.predictData(line));
    }
}
