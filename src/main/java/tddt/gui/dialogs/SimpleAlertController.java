package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    controls the simple-alert
 */
public class SimpleAlertController {

    // displays the message of the alert
    public Label label;
    // stage of the dialog
    private Stage stage;

    /*
        initializes the dialog
     */
    public void init(Stage stage, String msg){
        this.stage = stage;
        this.label.setText(msg);
    }

    /*
        closes the dialog
     */
    public void close(ActionEvent actionEvent) {
        this.stage.close();
    }

}
