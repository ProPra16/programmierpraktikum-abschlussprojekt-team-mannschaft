package main.java.tddt.gui.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.tddt.data.Log;

import java.time.format.DateTimeFormatter;

/**
 * Created by Roter Emu on 05.07.2016.
 */
public class ShowLogDialog {
    public ShowLogDialog(Stage main, Log log) throws Exception {
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(main);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/fxml/showLog.fxml"));
        Parent root = loader.load();
        ShowLogController controller = (ShowLogController) loader.getController();
        controller.init(stage, log);
        Scene scene = new Scene(root);
        String stylesheet = getClass().getResource("../../../../../resources/css/alert.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.setTitle("Log " + log.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss")));
        stage.setScene(scene);
        stage.showAndWait();
    }
}
