module com.t4.catalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens com.t4.catalog to javafx.fxml;
    exports com.t4.catalog;
}