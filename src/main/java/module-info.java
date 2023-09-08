module com.example.tpintegrador {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;
    requires java.sql;


    opens com.example.tpintegrador to javafx.fxml;
    exports com.example.tpintegrador;
}