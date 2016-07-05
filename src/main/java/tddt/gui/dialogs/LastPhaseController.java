package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */
public class LastPhaseController extends AlertController {

    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void ok(ActionEvent actionEvent) {
        this.controller.lastPhaseOutput();
        this.stage.close();
    }
}
