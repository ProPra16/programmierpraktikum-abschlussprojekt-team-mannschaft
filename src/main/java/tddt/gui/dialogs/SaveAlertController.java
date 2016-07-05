package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Roter Emu on 05.07.2016.
 */
public class SaveAlertController {

    public Label label;
    private Stage stage;

    public void init(Stage stage, String msg){
        this.stage = stage;
        this.label.setText("Your project was saved to: " + "\n" + msg);
    }



    public void close(ActionEvent actionEvent) {
        this.stage.close();
    }

}
