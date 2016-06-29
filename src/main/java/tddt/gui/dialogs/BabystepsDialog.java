package main.java.tddt.gui.dialogs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.tddt.gui.Controller;
import main.java.tddt.gui.GUIMain;

/**
 * Created by Roter Emu on 28.06.2016.
 */
public class BabystepsDialog {

    public BabystepsDialog(Stage main) throws Exception{
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(main);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/babysteps.fxml"));
        Parent root = loader.load();
        BabystepsController controller = (BabystepsController) loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root, 300, 300);
        String stylesheet = getClass().getResource("../resources/babysteps.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
        stage.getIcons().add(new Image(BabystepsDialog.class.getResourceAsStream("../resources/graphics/tddt.png")));
        stage.setTitle("Babysteps Configuration");
        stage.setScene(scene);
        stage.showAndWait();
    }

}
