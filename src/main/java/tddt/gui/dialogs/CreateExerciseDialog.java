package main.java.tddt.gui.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 02.07.2016.
 */

/*
    creates the CreateExercise-dialog
 */
public class CreateExerciseDialog {

    public CreateExerciseDialog(Stage main, Controller c) throws Exception{
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(main);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/fxml/createExercise.fxml"));
        Parent root = loader.load();
        CreateExerciseController controller = (CreateExerciseController) loader.getController();
        controller.init(stage, c);
        Scene scene = new Scene(root);
        root.requestFocus();
        String stylesheet = getClass().getResource("../../../../../resources/css/createExercise.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setTitle("Create Exercise");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
