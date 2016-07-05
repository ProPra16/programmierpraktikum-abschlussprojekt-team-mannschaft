package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;

/**
 * Created by Roter Emu on 05.07.2016.
 */
public class LastLogController extends AlertController{

    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    public void ok(ActionEvent actionEvent) {
        this.controller.undoOutput();
        this.stage.close();
    }
}
