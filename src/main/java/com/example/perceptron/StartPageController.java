package com.example.perceptron;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    public AnchorPane root;
    public Button trainingButton;
    public Button testButton;
    public Slider learningRateSlider;
    public TextField epochTextFiled;
    public Button nextButton;
    private String trainPath;
    private String testPath;

    public void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Select file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (selectedFile != null && event.getSource().equals(trainingButton)) {
            trainPath = selectedFile.getAbsolutePath();
            trainingButton.setText(selectedFile.getAbsolutePath());
        } else if (selectedFile != null && event.getSource().equals(testButton)) {
            testPath = selectedFile.getAbsolutePath();
            testButton.setText(selectedFile.getAbsolutePath());
        } else {
            System.out.println("Action canceled");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trainPath = "";
        testPath = "";
    }

    public void next(ActionEvent event) throws IOException {
        if (trainPath.equals("") || testPath.equals("") || epochTextFiled.getText().equals("")) {
            return;
        } else {
            int epoch = Integer.parseInt(epochTextFiled.getText());
            double learningRate = learningRateSlider.getValue();
            ProgramPageController programPageController = new ProgramPageController();
            programPageController.setPerceptronController(new PerceptronController(testPath, trainPath, epoch, learningRate));
            programPageController.load((Stage) root.getScene().getWindow());
        }
    }
}
