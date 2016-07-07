package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    controls the LastLog-alert
 */
public class LastLogController extends AlertController{

    /*
        cancels the dialog
     */
    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    /*
        gives the signal to return to last log-point
     */
    public void ok(ActionEvent actionEvent) {
        this.controller.undoOutput();
        this.stage.close();
    }
}
