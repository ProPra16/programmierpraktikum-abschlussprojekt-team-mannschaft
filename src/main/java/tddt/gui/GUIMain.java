package main.java.tddt.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

public class GUIMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller) loader.getController();
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("gui.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Test Driven Development Trainer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launchApp(String[] args){
        launch(args);
    }
}
