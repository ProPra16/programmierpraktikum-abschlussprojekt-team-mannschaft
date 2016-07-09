package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    controls the LastPhase-alert
 */
public class LastPhaseController extends AlertController {

    /*
        cancels the dialog
     */
    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    /*
        gives the signal to return to last phase
     */
    public void ok(ActionEvent actionEvent) {
        this.controller.lastPhaseOutput();
        this.stage.close();
    }
}
