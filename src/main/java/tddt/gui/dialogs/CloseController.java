package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    controls the close-alert
 */
public class CloseController extends AlertController {

    /*
        closes with saving
     */
    public void save(ActionEvent actionEvent) {
        this.stage.close();
        this.controller.closeOutput();
    }

    /*
        cancels the close-request
     */
    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }
}
