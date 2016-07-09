package main.java.tddt.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    // shows the graph-object
    public TabPane tabpane;

    // main stage of the application
    private Stage stage;
    // reference to the coordinator of the current project
    private Coordinator c;
    // directory where all user-created files are saved
    private File initialFile;
    // contains the series of timer-values for the graph
    private ObservableList<Series<Double, Double>> lineChartData;
    // contains the timer-values of phase 1, 2 and 3
    private Series<Double, Double>[] phases;
    // counts the overall amount of phases in the project
    private int phaseCounter;

    /*
        Constructor of Controller:
        initializes initalFile or creates the directory if it does not exist
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
        initializes the main stage of the application and sets the appearance of the window to phase 1
     */
    public void init(Stage stage) {
        this.stage = stage;
        this.setPhase(1);
        this.graph.prefHeightProperty().bind(this.tabpane.prefHeightProperty());
        this.graph.prefWidthProperty().bind(this.tabpane.prefWidthProperty());
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
            Exercise chosen = Exercise.getExercise(choose.showOpenDialog(this.stage));
            File projectFiles = new File(initialFile, chosen.getTitle() + "/Logs");
            if(projectFiles.exists()){
                try{
                    new Alert(this.stage, Alert.SIMPEL_ALERT, "The project already exists");
                }catch(Exception e){}
            }
            else {
                projectFiles.mkdirs();
                this.classpane.setText(chosen.getClasstext());
                this.testpane.setText(chosen.getTesttext());
                this.descriptionpane.setText(chosen.getDescription());
                this.classtitled.setText(chosen.getTitle());
                this.testtitled.setText(chosen.getTitle() + "Test");
                this.c = new Coordinator(chosen.getTitle(), chosen.getTitle() + "Test", 1, projectFiles, this.clock, this);
                this.graphInit();
                this.phaseCounter = 0;
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
            File chosen = choose.showDialog(this.stage);
            ProjectIO project = ProjectIO.getProject(new File(chosen, File.separator + chosen.getName()));
            this.classpane.setText(project.getClasstext());
            this.testpane.setText(project.getTesttext());
            this.descriptionpane.setText(project.getDescription());
            this.classtitled.setText(project.getTitle());
            this.testtitled.setText(project.getTitle() + "Test");
            this.c = new Coordinator(project.getTitle(), project.getTitle() + "Test", project.getPhase(), new File(chosen, File.separator + "Logs"), this.clock, this);
            this.phaseCounter = 0;
            this.setPhase(project.getPhase());
            this.graphInit(this.c.getPhaseTimes());
        } catch(Exception e) {}
    }

    /*
        lets the user save the current state of the project
        informs the user about the location the project was saved to
     */
    public void saveProject() {
        if(this.c != null) {
            File dest = new File(initialFile, this.classtitled.getText());
            try {
                ProjectIO.saveProject(this.classtitled.getText(), this.descriptionpane.getText(), this.classpane.getText(), this.testpane.getText(), this.c.phase, this.c.getBabystepsActivated(), this.c.getBabystepsTime(), dest);
                BufferedWriter classWriter = new BufferedWriter(new FileWriter(new File(this.initialFile, File.separator + this.classtitled.getText() + File.separator + this.classtitled.getText() + ".java")));
                BufferedWriter testWriter = new BufferedWriter(new FileWriter(new File(this.initialFile, File.separator + this.classtitled.getText() + File.separator + this.testtitled.getText() + ".java")));
                classWriter.write(this.classpane.getText());
                testWriter.write(this.testpane.getText());
                classWriter.close();
                testWriter.close();
            } catch (Exception e) {
                try {
                    new Alert(this.stage, Alert.SIMPEL_ALERT, "ERROR - Your project is incomplete");
                    return;
                } catch (Exception d) {}
            }
            try {
                new Alert(this.stage, Alert.SIMPEL_ALERT, "Your project was saved to: " + "\n" + dest.getAbsolutePath());
            } catch (Exception e) {}
        }
    }

    /*
        lets compile and test the project by the coordinator and prints results to the terminal
     */
    public void runTest() {
        if(this.c != null) {
            this.consolepane.setText(this.c.compile(this.classpane.getText(), this.testpane.getText()));
        }
    }

    /*
        lets the user delete all changes, that were made since the last log-point was created
        log-points are created after every run
     */
    public void undo() {
        if(this.c != null) {
            try {
                new Alert(this.stage, this, Alert.LAST_LOG);
            } catch (Exception e) {}
        }
    }

    /*
        lets the user choose a specific log out of the log-list and displays its contents
     */
    public void showLog() {
        if(this.c != null) {
            try {
                FileChooser choose = new FileChooser();
                choose.setInitialDirectory(new File(initialFile, this.classtitled.getText() + "/Logs"));
                choose.setTitle("Select Log");
                Log log = Log.getLog(choose.showOpenDialog(this.stage));
                if (log != null) {
                    new ShowLogDialog(stage, log);
                }
            } catch (Exception e) {}
        }
    }

    /*
        lets the user delete the whole log-list
     */
    public void deleteLog() {
        if(this.c != null) {
            try {
                new Alert(this.stage, this, Alert.DELETE_LOG);
            } catch (Exception e) {}
        }
    }

    /*
        opens the babysteps-dialog
        lets the user configure babysteps
        new configurations will affect the next and following phases; not the current phase
     */
    public void babysteps() {
        if(this.c != null) {
            try {
                new BabystepsDialog(this.stage, this.c.getBabystepsActivated(), this.c.getBabystepsTime(), this);
            } catch (Exception e) {}
        }
    }

    /*
        lets the user return to last phase
        all changes, that were made in the current phase are deleted
     */
    public void lastPhase() {
        if(this.c != null) {
            try {
                new Alert(this.stage, this, Alert.LAST_PHASE);
            } catch (Exception e) {}
        }
    }

    /*
        lets the user go on to the next phase
        the coordinator checks whether this is valid or not
     */
    public void nextPhase() {
        if(this.c != null) {
            int lastphase = this.c.phase;
            LocalDateTime phasetime = null;
            try {
                phasetime = this.c.nextPhase(this.classpane.getText(), this.testpane.getText());
            } catch(Exception e) {}
            if (phasetime == null) {
                try {
                    new Alert(this.stage, Alert.SIMPEL_ALERT, "Changing phases was not permitted!");
                } catch (Exception e) {}
            }
            else {
                this.setPhase(this.c.phase);
                this.updateGraph(lastphase, phasetime);
            }
        }
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
    public void exerciseOutput(String title, String desc) throws Exception {
        Exercise.createExercise(title, desc, new File(initialFile, File.separator + "exercises"));
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
        try {
            Log log = this.c.lastPhase();
            if(log != null) {
                this.classpane.setText(log.getClassText());
                this.testpane.setText(log.getTestText());
                this.consolepane.setText(log.getCompileMessage());
                if (log.getTimer() != null) {
                    this.clock.setText(log.getTimer().format(DateTimeFormatter.ofPattern("mm:ss")));
                }
                this.setPhase(this.c.phase);
                this.deleteLastGraph(this.c.phase);
            }
            else{
                try{
                    new Alert(this.stage, Alert.SIMPEL_ALERT, "No logs existing. Changing phase not possible");
                } catch(Exception e){}
            }
        } catch (Exception e) {}
    }

    /*
        corresponding method: deleteLog()
     */
    public void deleteLogOutput() {
        this.c.deleteLog();
    }

    /*
        corresponding method: undoOutput()
     */
    public void undoOutput() {
        try {
            Log log = this.c.lastLog();
            this.classpane.setText(log.getClassText());
            this.testpane.setText(log.getTestText());
            this.consolepane.setText(log.getCompileMessage());
            if(log.getTimer() != null) {
                this.clock.setText(log.getTimer().format(DateTimeFormatter.ofPattern("mm:ss")));
            }
        } catch (Exception e) {}
    }

    /*
        corresponding method: babysteps()
     */
    public void babystepsOutput(boolean activated, double minutes){
         this.c.setBabystepsActivated(activated, minutes);
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
        initializes the tracking graph, after opening an existing project
     */
    private void graphInit(ArrayList<LocalDateTime>[] phaseTimes) {
        this.graphInit();
        for(int i = 0; i < phaseTimes[0].size(); i++){
            this.phaseCounter++;
            int min = phaseTimes[0].get(i).getMinute();
            int sec = phaseTimes[0].get(i).getSecond();
            double timer = min + sec/60.0;
            this.phases[0].getData().add(new XYChart.Data<>((double) this.phaseCounter, timer));
        }
        for(int i = 0; i < phaseTimes[1].size(); i++){
            this.phaseCounter++;
            int min = phaseTimes[1].get(i).getMinute();
            int sec = phaseTimes[1].get(i).getSecond();
            double timer = min + sec/60.0;
            this.phases[1].getData().add(new XYChart.Data<>((double) this.phaseCounter, timer));
        }
        for(int i = 0; i < phaseTimes[2].size(); i++){
            this.phaseCounter++;
            int min = phaseTimes[2].get(i).getMinute();
            int sec = phaseTimes[2].get(i).getSecond();
            double timer = min + sec/60.0;
            this.phases[2].getData().add(new XYChart.Data<>((double) this.phaseCounter, timer));
        }
    }

    /*
        initializes all objects concerning the graph
     */
    private void graphInit(){
        this.lineChartData = FXCollections.observableArrayList();
        this.phases = new Series[3];
        this.phases[0] = new Series<>();
        this.phases[0].setName("RED");
        this.phases[1] = new Series<>();
        this.phases[1].setName("GREEN");
        this.phases[2] = new Series<>();
        this.phases[2].setName("REFACTOR");
        this.lineChartData.addAll(this.phases[0], this.phases[1], this.phases[2]);
        this.graph.setData(lineChartData);
    }

    /*
        attaches the given value to the tracking graph
     */
    private void updateGraph(int phase, LocalDateTime phasetime) {
        this.phaseCounter++;
        int min = phasetime.getMinute();
        int sec = phasetime.getSecond();
        double timer = min + sec/60.0;
        this.phases[phase-1].getData().add(new XYChart.Data<>((double) this.phaseCounter, timer));
    }

    /*
        deletes the last point of the graph
     */
    private void deleteLastGraph(int phase) {
        this.phaseCounter--;
        int index = this.phases[phase-1].getData().size()-1;
        this.phases[phase-1].getData().remove(index);
    }

    /*
        setzt alle Daten auf den Zustand vom Beginn der Phase, wenn der Babystepstimer abgelaufen ist
     */
    public void timeoveratBabystepping(Log log){
        this.testpane.setText(log.getTestText());
        this.classpane.setText(log.getClassText());
        this.consolepane.setText(log.getCompileMessage());
        if(log.getTimer() != null) {
            this.clock.setText(log.getTimer().format(DateTimeFormatter.ofPattern("mm:ss")));
        }
    }
}