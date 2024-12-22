module rw.app.finalgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens rw.app.finalgui to javafx.fxml;
    exports rw.app.finalgui;
}
