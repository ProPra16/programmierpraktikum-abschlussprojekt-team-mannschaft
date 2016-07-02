package main.java.tddt.gui;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.java.tddt.Coordinator;
import main.java.tddt.gui.dialogs.BabystepsDialog;
import main.java.tddt.gui.dialogs.CreateExerciseDialog;

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
    private Coordinator c;

    public void selectExercise() {
    }

    public void openProject() {
    }

    public void runTest() {
        this.c = new Coordinator("Fak", "FakTest", this);
        this.consolepane.setText(this.c.compile(this.classpane.getText(), this.testpane.getText()));
    }

    public void saveProject() {
        //this.c.save(this.classpane.getText(), this.testpane.getText());
    }

    public void babysteps() {
        try {
            new BabystepsDialog(stage, true, 4.5, this); // coordinator muss init daten liefern
        }
        catch(Exception e){}
    }

    public void babystepsOutput(boolean activated, double minutes){
        // this.c.setBabystepsActivated(activated, minutes);
    }

    public void createExercise() {
        try{
            new CreateExerciseDialog(stage, this);
        }
        catch(Exception e){e.printStackTrace();}
    }

    public void showLog() {
    }

    public void deleteLog() {
        //this.c.deleteLog();
    }

    public void lastPhase() {
        //this.c.setPhase(c.phase -1);
    }

    public void undo() {
        //this.c.undo();
    }

    public void handleTerminal() {
        double[] pos = this.mainSplitPane.getDividerPositions();
        if(pos[0] < 0.99){
            this.mainSplitPane.setDividerPosition(0,1.0);
        }
        else{
            this.mainSplitPane.setDividerPosition(0,0.6);
        }
    }

    public void init(Stage stage) {
        this.stage = stage;
    }

    public void nextPhase() {
        this.c.nextPhase(this.classpane.getText(), this.testpane.getText());
    }

    public void exerciseOutput(String title, String desc) {
        //this.c.createExercise(title, desc);
    }

    public void setPhase(int i){
        switch(i){
            case 1:
                phaselabel.setText("RED");
                phaselabel.setId("red");
                classpane.setId("inactive");
                testpane.setId(null);
                break;
            case 2:
                phaselabel.setText("GREEN");
                phaselabel.setId("green");
                testpane.setId("inactive");
                classpane.setId(null);
                break;
            case 3:
                phaselabel.setText("REFACTOR");
                phaselabel.setId("refactor");
                testpane.setId("inactive");
                classpane.setId(null);
                break;
        }
    }

}
