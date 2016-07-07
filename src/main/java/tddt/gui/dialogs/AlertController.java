package main.java.tddt.gui.dialogs;

import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    provides the init-Method for AlertControllers
    is extended by all AlertControllers except from the one of the SIMPLE_ALERT
 */
public abstract class AlertController {

    // stage of the Alert
    protected Stage stage;
    // reference to Controller to handle user-input
    protected Controller controller;

    // inits the alert
    public void init(Stage stage, Controller controller){
        this.stage = stage;
        this.controller = controller;
    }
}
