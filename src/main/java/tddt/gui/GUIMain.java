package main.java.tddt.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by Roter Emu on 23.06.2016.
 */

/*
    creates the window of the application
 */
public class GUIMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../resources/fxml/gui.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller) loader.getController();
        controller.init(primaryStage);
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("../../../../resources/css/gui.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        primaryStage.getIcons().add(new Image(GUIMain.class.getResourceAsStream("../../../../resources/graphics/tddt.png")));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Test Driven Development Trainer");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                we.consume();
                controller.close();
            }
        });
    }

    public static void launchGUI(String[] args){
        launch(args);
    }

}
