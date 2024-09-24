module ResultApp {
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	
	exports ci.exam;
	opens ci.exam to javafx.fxml;
    opens ci.exam.controllers.ui to javafx.fxml;
    opens ci.exam.model to javafx.fxml;
    exports ci.exam.controllers.ui;
    exports ci.exam.model;
}