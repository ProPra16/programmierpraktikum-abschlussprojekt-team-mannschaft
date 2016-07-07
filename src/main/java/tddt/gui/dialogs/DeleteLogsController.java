package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    controls the DeleteLogs-alert
 */
public class DeleteLogsController extends AlertController {

    /*
        cancels the dialog
     */
    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    /*
        gives the signal to delete all log-files
     */
    public void delete(ActionEvent actionEvent) {
        this.controller.deleteLogOutput();
        this.stage.close();
    }
}
