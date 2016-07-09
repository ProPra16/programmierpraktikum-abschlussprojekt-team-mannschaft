package main.java.tddt.gui.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 28.06.2016.
 */

/*
    creates the BabySteps-Dialog
 */
public class BabystepsDialog {

    public BabystepsDialog(Stage main, boolean active, double minutes, Controller c) throws Exception{
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(main);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/fxml/babysteps.fxml"));
        Parent root = loader.load();
        BabystepsController controller = (BabystepsController) loader.getController();
        controller.init(active, minutes, c, stage);
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("../../../../../resources/css/babysteps.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setTitle("Babysteps Configuration");
        stage.setScene(scene);
        stage.showAndWait();
    }

}
