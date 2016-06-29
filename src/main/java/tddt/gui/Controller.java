package main.java.tddt.gui;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.java.tddt.gui.dialogs.BabystepsDialog;

public class Controller {

    public Label phaselabel;
    public Label clockpic;
    public Label clock;
    public TextArea classpane;
    public TextArea testpane;
    public Label consolepane;
    public Label descriptionpane;
    public Label graphpane;
    public SplitPane mainSplitPane;
    private Stage stage;

    public void selectExercise(ActionEvent actionEvent) {
    }

    public void openExercise(ActionEvent actionEvent) {
    }

    public void runTest(ActionEvent actionEvent) {
    }

    public void saveProject(ActionEvent actionEvent) {
    }

    public void babysteps(ActionEvent actionEvent) {
        try {
            BabystepsDialog dialog = new BabystepsDialog(stage);
        }
        catch(Exception e){e.printStackTrace();}
    }

    public void createExercise(ActionEvent actionEvent) {
    }

    public void showLog(ActionEvent actionEvent) {
    }

    public void deleteLog(ActionEvent actionEvent) {
    }

    public void goBack(ActionEvent actionEvent) {
    }

    public void undo(ActionEvent actionEvent) {
    }

    public void handleTerminal(ActionEvent actionEvent) {
        double[] pos = this.mainSplitPane.getDividerPositions();
        if(pos[0] < 0.99){
            this.mainSplitPane.setDividerPosition(0,1.0);
        }
        else{
            this.mainSplitPane.setDividerPosition(0,0.6);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void nextPhase(ActionEvent actionEvent) {
    }

    public void close(ActionEvent actionEvent) {
        stage.close();
    }

    public void maximize(ActionEvent actionEvent) {
    }

    public void minimize(ActionEvent actionEvent) {
    }
}
