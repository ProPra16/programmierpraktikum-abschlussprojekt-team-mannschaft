package main.java.tddt.gui.dialogs;

import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */
public abstract class AlertController {

    protected Stage stage;
    protected Controller controller;

    public void init(Stage stage, Controller controller){
        this.stage = stage;
        this.controller = controller;
    }
}
