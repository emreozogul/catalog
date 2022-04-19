module com.t4.catalog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.t4.catalog to javafx.fxml;
    exports com.t4.catalog;
}