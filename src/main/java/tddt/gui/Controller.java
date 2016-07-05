package main.java.tddt.gui;

import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.tddt.Coordinator;
import main.java.tddt.data.CreateExerciseCatalog;
import main.java.tddt.data.Exercise;
import main.java.tddt.data.ProjectIO;
import main.java.tddt.gui.dialogs.Alert;
import main.java.tddt.gui.dialogs.BabystepsDialog;
import main.java.tddt.gui.dialogs.CreateExerciseDialog;
import main.java.tddt.data.Log;
import main.java.tddt.gui.dialogs.ShowLogDialog;

import java.io.File;

public class Controller {

    // shows the phase (dynamic)
    public Label phaselabel;
    // shows the timer (dynamic)
    public Label clock;
    // shows and edits the class
    public TextArea classpane;
    // shows and edits the test
    public TextArea testpane;
    // shows compile and test results, when application project runs
    public Label consolepane;
    // shows the description of the current exercise
    public Label descriptionpane;
    // splits the window into editor and terminal
    public SplitPane mainSplitPane;
    // test-editor
    public TitledPane testtitled;
    // class-editor
    public TitledPane classtitled;
    // shows the tracking graph
    public LineChart graph;

    // main stage of the application
    private Stage stage;
    // reference to the coordinator of the current project
    private Coordinator c;
    // directory where all user-created files are saved
    private File initialFile;

    /*
        Constructor of Controller:
        initialises initalFile or creates the directory if it does not exist
        if this is the case it additionally creates a copy of the exercise-catalog out of the projects resources
     */
    public Controller(){
        this.initialFile = new File(System.getProperty("user.home"), ".TestDrivenDevelopmentTrainer");
        if(!this.initialFile.exists()) {
            this.initialFile.mkdirs();
            File exec = new File(initialFile, "exercises");
            try{
                new CreateExerciseCatalog(new File("src/resources/exercises"), exec);

            } catch (Exception e) {}
        }
    }

    /*
        initialises the main stage of the application and sets the appearance of the window to phase 1
     */
    public void init(Stage stage) {
        this.stage = stage;
        this.setPhase(1);
    }

    /*
        lets the user choose an exercise to work on from the local copy of the exercise-catalog
        therefore self-made exercises are included!
        creates all directories, that are needed for the project
     */
    public void selectExercise() {
        try {
            File exec = new File(initialFile, "exercises");
            FileChooser choose = new FileChooser();
            choose.setInitialDirectory(exec);
            choose.setTitle("Select Exercise");
            choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
            File exercise = choose.showOpenDialog(this.stage);
            if (exercise != null) {
                Exercise chosen = Exercise.getExercise(exercise);
                this.classpane.setText(chosen.getClassText());
                this.testpane.setText(chosen.getTestText());
                this.descriptionpane.setText(chosen.getDescription());
                this.classtitled.setText(chosen.getTitle());
                this.testtitled.setText(chosen.getTitle() + "Test");
                this.c = new Coordinator(chosen.getTitle(), chosen.getTitle() + "Test");
                File projectFiles = new File(initialFile, chosen.getTitle() + "/Logs");
                projectFiles.mkdirs();
            }
        } catch(Exception e) {}
    }

    /*
        opens the dialog that lets the user create a new exercise
        this exercise will only be saved to the local copy of the exercise-catalog
     */
    public void createExercise() {
        try{
            new CreateExerciseDialog(this.stage, this);
        } catch (Exception e) {}
    }

    /*
        lets the user choose a project he/she already worked on
     */
    public void openProject() {
        try {
            DirectoryChooser choose = new DirectoryChooser();
            choose.setInitialDirectory(initialFile);
            choose.setTitle("Select Project");
            String[] data = ProjectIO.getProject(choose.showDialog(this.stage));
            this.classpane.setText(data[2]);
            this.testpane.setText(data[3]);
            this.descriptionpane.setText(data[1]);
            this.classtitled.setText(data[0]);
            this.testtitled.setText(data[0] + "Test");
            this.c = new Coordinator(data[0], data[0] + "Test", Integer.parseInt(data[4]));
            this.setPhase(Integer.parseInt(data[4]));

            // Hier noch Tracking Graph

        } catch(Exception e) {}
    }

    /*
        lets the user save the current state of the project
        informs the user about the location the project was saved to
     */
    public void saveProject() {
        File dest =  new File(initialFile, this.classtitled.getText());
        try{
            ProjectIO.saveProject(this.classtitled.getText(), this.descriptionpane.getText(), this.classpane.getText(), this.testpane.getText(), String.valueOf(this.c.phase), dest);
        } catch(Exception e) {
            try {
                new Alert(this.stage, Alert.SAVE_PROJECT, "ERROR - Your project is incomplete");
                return;
            } catch(Exception d) {}
        }
        try {
            new Alert(this.stage, Alert.SAVE_PROJECT, dest.getAbsolutePath());
        } catch(Exception e) {e.printStackTrace();}
    }

    /*
        lets compile and test the project by the coordinator and prints results to the terminal
     */
    public void runTest() {
        this.consolepane.setText(this.c.compile(this.classpane.getText(), this.testpane.getText()));
    }

    /*
        lets the user delete all changes, that were made since the last log-point was created
        log-points are created after every run
     */
    public void undo() {
        try{
            new Alert(this.stage, this, Alert.LAST_LOG);
        } catch (Exception e) {}
    }

    /*
        lets the user choose a specific log out of the log-list and displays its contents
     */
    public void showLog() {
        try {
            FileChooser choose = new FileChooser();
            choose.setInitialDirectory(new File(initialFile, this.classtitled.getText() + "/Logs"));
            choose.setTitle("Select Log");
            choose.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
            Log log = Log.getLog(choose.showOpenDialog(this.stage));
            if(log != null) {
                new ShowLogDialog(stage, log);
            }
        } catch (Exception e) {}
    }

    /*
        lets the usere delete the whole log-list
     */
    public void deleteLog() {
        try{
            new Alert(this.stage, this, Alert.DELETE_LOG);
        } catch (Exception e) {}
    }

    /*
        opens the babysteps-dialog
        lets the user configure babysteps
        new configurations will affect the next and following phases; not the current phase
     */
    public void babysteps() {
        try {
            new BabystepsDialog(this.stage, true, 4.5, this); // coordinator muss init daten liefern
        } catch (Exception e) {}
    }

    /*
        lets the user return to last phase
        all changes, that were made in the current phase are deleted
     */
    public void lastPhase() {
        try{
            new Alert(this.stage, this, Alert.LAST_PHASE);
        } catch (Exception e) {}
    }

    /*
        lets the user go on to the next phase
        the coordinator checks whether this is valid or not
     */
    public void nextPhase() {
        // Hier noch meldung falls Phasenwechsel unzulässig
        this.c.nextPhase(this.classpane.getText(), this.testpane.getText());
        this.setPhase(this.c.phase);
    }

    /*
        shows or hides the terminal; depending on current state
     */
    public void handleTerminal() {
        double[] pos = this.mainSplitPane.getDividerPositions();
        if(pos[0] < 0.99){
            this.mainSplitPane.setDividerPosition(0,1.0);
        }
        else{
            this.mainSplitPane.setDividerPosition(0,0.6);
        }
    }

    /*
        asks the user, if he/she wants to save the project before closing the application
     */
    public void close() {
        try{
            new Alert(this.stage, this, Alert.CLOSE);
        } catch (Exception e) {}
    }


    /*
        following Output-Methods are used by the corresponding alerts from above
        for more information look above
     */

    /*
        corresponding method: createExercise()
     */
    public void exerciseOutput(String title, String desc) {
        Exercise.createExercise(title, desc, new File(initialFile, "exercises"));
    }

    /*
        corresponding method: close()
     */
    public void closeOutput(boolean save){
        if(save){
            this.saveProject();
            this.stage.close();
        }
        else {
            this.stage.close();
        }
    }

    /*
        corresponding method: lastPhase()
     */
    public void lastPhaseOutput() {
        this.setPhase(c.phase-1);
        //Log log = this.c.lastPhase();
    }

    /*
        corresponding method: deleteLog()
     */
    public void deleteLogOutput() {
        //this.c.deleteLog();
    }

    /*
        corresponding method: undoOutput()
     */
    public void undoOutput() {
        //this.c.lastLog();
    }

    /*
        corresponding method: babysteps()
     */
    public void babystepsOutput(boolean activated, double minutes){
        // this.c.setBabystepsActivated(activated, minutes);
    }

    /*
        sets the appearance of the application to the given phase
     */
    private void setPhase(int i){
        switch(i){
            case 1:
                phaselabel.setText("RED");
                phaselabel.setId("red");
                classpane.setDisable(true);
                testpane.setDisable(false);
                break;
            case 2:
                phaselabel.setText("GREEN");
                phaselabel.setId("green");
                testpane.setDisable(true);
                classpane.setDisable(false);
                break;
            case 3:
                phaselabel.setText("REFACTOR");
                phaselabel.setId("refactor");
                testpane.setDisable(true);
                classpane.setDisable(false);
                break;
        }
    }

    /*
        sets the timer
     */
    public void setClock(String time){
        this.clock.setText(time);
    }
}