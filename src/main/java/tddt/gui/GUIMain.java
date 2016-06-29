package main.java.tddt.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUIMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/gui.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller) loader.getController();
        controller.setStage(primaryStage);
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("resources/gui.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        primaryStage.getIcons().add(new Image(GUIMain.class.getResourceAsStream("resources/graphics/tddt.png")));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Test Driven Development Trainer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launchGUI(String[] args){
        launch(args);
    }

}
