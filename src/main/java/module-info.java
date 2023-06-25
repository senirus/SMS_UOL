module com.example.sms_uol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.management.agent;


    opens com.example.sms_uol to javafx.fxml;
    exports com.example.sms_uol;
}