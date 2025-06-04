module com.example.strangespacejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.strangespacejava to javafx.fxml;
    exports com.example.strangespacejava;
}