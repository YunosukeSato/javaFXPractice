module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.fxml;
    opens com.example.login to javafx.fxml;
    opens com.example.home to javafx.fxml;

    exports com.example;
    exports com.example.login;
    exports com.example.home;
}
