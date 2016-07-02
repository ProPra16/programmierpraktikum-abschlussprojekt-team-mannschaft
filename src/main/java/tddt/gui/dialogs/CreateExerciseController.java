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
public class CreateExerciseController {

    public TextArea desclabel;
    public TextArea titlelabel;
    public Label alert;
    private Controller c;
    private Stage stage;


    public void init(Stage stage, Controller c){
        this.c = c;
        this.stage = stage;
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }

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
