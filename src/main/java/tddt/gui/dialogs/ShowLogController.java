package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.tddt.data.Log;

import java.time.format.DateTimeFormatter;

/**
 * Created by Roter Emu on 05.07.2016.
 */

/*
    controls the ShowLogDialog
 */
public class ShowLogController {

    // displays time of Log
    public Label date;
    // displays timer of log
    public Label timer;
    // displays classtext of log
    public Label classfile;
    // displays compileMessage of log
    public Label compile;
    // displays testtext of log
    public Label testfile;
    // displays phase of log
    public Label phase;
    // stage of the dialog
    private Stage stage;

    /*
        closes the dialog
     */
    public void ok(ActionEvent actionEvent) {
        this.stage.close();
    }

    /*
        initializes all labels
     */
    public void init(Stage stage, Log log){
        this.stage = stage;
        this.date.setText(log.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss")));
        this.timer.setText(log.getTime().format(DateTimeFormatter.ofPattern("mm:ss")));
        this.classfile.setText(log.getClassText());
        this.testfile.setText(log.getTestText());
        this.compile.setText(log.getCompileMessage());
        switch(log.getPhase()){
            case 1: this.phase.setText("RED");
                break;
            case 2: this.phase.setText("GREEN");
                break;
            case 3: this.phase.setText("REFACTOR");
                break;
        }
    }
}
