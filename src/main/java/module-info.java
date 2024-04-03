module com.example.perceptron {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.perceptron to javafx.fxml;
    exports com.example.perceptron;
}