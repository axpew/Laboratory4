module ucr.laboratory4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.laboratory4 to javafx.fxml;
    exports ucr.laboratory4;
    exports controller;
    opens controller to javafx.fxml;

    opens domain to javafx.base;
}