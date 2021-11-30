module Bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires org.jetbrains.annotations;

    opens uet.oop.bomberman to javafx.fxml;
    exports uet.oop.bomberman;
}