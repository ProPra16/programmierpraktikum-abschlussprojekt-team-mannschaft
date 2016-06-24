package tddt.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUIMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();
        Controller controller = (Controller) loader.getController();
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("gui.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        Label description = new Label(" Dies ist ein TestString" + "\n" + " mit einer zweiten zeile");
        description.setRotate(270);

        TitledPane pane = new TitledPane("Exercise Description", description);
        pane.setAlignment(Pos.CENTER);
        pane.setRotate(90);
        pane.setId("test");

        GridPane main = new GridPane();
        controller.terminalpane.add(main,1,0);
        main.setAlignment(Pos.CENTER_RIGHT);
        pane.prefWidthProperty().bind(main.heightProperty());
        pane.prefHeightProperty().bind(main.widthProperty());

        main.add(pane,0,0);

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Test Driven Development Trainer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launchApp(String[] args){
        launch(args);
    }
}
