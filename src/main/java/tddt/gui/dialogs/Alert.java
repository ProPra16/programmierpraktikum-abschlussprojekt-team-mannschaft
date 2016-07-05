package main.java.tddt.gui.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */
public class Alert {

    public static final String DELETE_LOG = "deleteLogs";
    public static final String LAST_PHASE = "lastPhase";
    public static final String CLOSE = "close";
    public static final String LAST_LOG = "lastLog";
    public static final String SAVE_PROJECT = "saveProject";

    public Alert(Stage main, Controller c, String type) throws Exception{
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(main);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/fxml/" + type + ".fxml"));
        Parent root = loader.load();
        AlertController controller = (AlertController) loader.getController();
        controller.init(stage, c);
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("../../../../../resources/css/alert.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setTitle("Attention");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public Alert(Stage main, String type, String msg) throws Exception{
        if(!type.equals(SAVE_PROJECT)){
            throw new Exception();
        }
        else{
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(main);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/fxml/" + type + ".fxml"));
            Parent root = loader.load();
            SaveAlertController controller = (SaveAlertController) loader.getController();
            controller.init(stage, msg);
            Scene scene = new Scene(root);
            String stylesheet = getClass().getResource("../../../../../resources/css/alert.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);
            stage.setTitle("Attention");
            stage.setScene(scene);
            stage.showAndWait();
        }
    }
}
