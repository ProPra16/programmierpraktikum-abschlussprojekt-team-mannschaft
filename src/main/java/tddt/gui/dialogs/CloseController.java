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
        closes without saving
     */
    public void ok(ActionEvent actionEvent) {
        this.controller.closeOutput(false);
        this.stage.close();
    }

    /*
        closes with saving
     */
    public void save(ActionEvent actionEvent) {
        this.controller.closeOutput(true);
        this.stage.close();
    }

    /*
        cancels the close-request
     */
    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }
}
