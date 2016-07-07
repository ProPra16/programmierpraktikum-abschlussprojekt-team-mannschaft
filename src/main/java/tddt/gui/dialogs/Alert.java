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

/*
    creates different alerts
 */
public class Alert {

    /*
        following variables specify the type of the alert
     */
    public static final String DELETE_LOG = "deleteLogs";
    public static final String LAST_PHASE = "lastPhase";
    public static final String CLOSE = "close";
    public static final String LAST_LOG = "lastLog";
    public static final String SIMPEL_ALERT = "simpleAlert";

    /*
        creates all alerts except from the SIMPLE_ALERT
     */
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

    /*
        creates the SIMPLE_ALERT
     */
    public Alert(Stage main, String type, String msg) throws Exception{
        if(!type.equals(SIMPEL_ALERT)){
            throw new Exception();
        }
        else{
            Stage stage = new Stage(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(main);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/fxml/" + type + ".fxml"));
            Parent root = loader.load();
            SimpleAlertController controller = (SimpleAlertController) loader.getController();
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
