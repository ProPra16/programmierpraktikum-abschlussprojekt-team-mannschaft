package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;

import javax.sound.sampled.Control;

/**
 * Created by Roter Emu on 02.07.2016.
 */

/*
    controls the CreateExerciseDialog
 */
public class CreateExerciseController {

    // area to enter the description of the exercise
    public TextArea desclabel;
    // area to enter the title of the exercise
    public TextArea titlelabel;
    // shows an alert when user tries to enter invalid information, which means either description or title are unset
    public Label alert;
    // reference to controller to handle user-input
    private Controller c;
    // stage of the Dialog
    private Stage stage;

    /*
        initialize
     */
    public void init(Stage stage, Controller c){
        this.c = c;
        this.stage = stage;
    }

    /*
        cancels the dialog
     */
    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

    /*
        checks if input is not null and creates the corresponding exercise
     */
    public void enter(ActionEvent actionEvent) {
        if(desclabel.getText().equals("") || titlelabel.getText().equals("")){
            alert.setText("Insert valid information please");
        }
        else {
            c.exerciseOutput(titlelabel.getText(), desclabel.getText());
            stage.close();
        }
    }
}
