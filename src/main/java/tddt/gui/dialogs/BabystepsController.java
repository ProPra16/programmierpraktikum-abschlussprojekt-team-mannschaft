package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.tddt.gui.Controller;


/**
 * Created by Roter Emu on 28.06.2016.
 */

/*
    controlls the BabystepsDialog
 */
public class BabystepsController {

    // true if babysteps are activated, false if not
    private boolean active;
    // true if there have been made changes to the configuration of babysteps, false if not
    private boolean changedstate = false;
    // initial length of a babystep
    private String minutes;
    // stage of the BabystepsDialog
    private Stage stage;
    // reference to Controller to handle user-input
    private Controller c;

    // Babysteps activated checkbox
    public CheckBox checkbox;
    // simple text-label with text: Time per phase in minutes
    public Label timelabel;
    // Spinner that lets the user select the length of a babystep
    public Spinner time;
    // apply-button
    public Button apply;

    /*
        gets the new configuration of babysteps and gives it to the controller
     */
    public void apply(ActionEvent actionEvent) {
        if((this.checkbox.isSelected() && this.changedMins()) || this.changedstate) {
            this.c.babystepsOutput(this.checkbox.isSelected(), Double.parseDouble(this.minutes));
            this.stage.close();
        }
    }

    /*
        closes the window without changing the configuration of babysteps
     */
    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

    /*
        sets the Id's of components to inactive or active corresponding to the state of the checkbox
        checks whether there have been made changes to the configuration
     */
    public void handleCheckBox(ActionEvent actionEvent) {
        if(this.checkbox.isSelected()){
            this.timelabel.setId(null);
            this.time.getEditor().setId(null);
        }
        else{
            this.timelabel.setId("inactivelabel");
            this.time.getEditor().setId("inactivespinner");
        }
        if(!(this.checkbox.isSelected() == this.active)){
            this.changedstate = true;
        }
        else{
            this.changedstate = false;
        }
        if((this.checkbox.isSelected() && this.changedMins()) || this.changedstate) {
            this.apply.setId(null);
        }
        else{
            this.apply.setId("inactivebutton");
        }
    }

    /*
        returns whether the length of babysteps has been changed
     */
    private boolean changedMins() {
        try {
            return !(Double.parseDouble(this.minutes) == Double.parseDouble(this.time.getEditor().getText()));
        }
        catch(Exception e){ return false; }
    }

    /*
        initializes the Babysteps-Dialog
     */
    public void init(boolean active, double minutes, Controller c, Stage stage) {
        this.stage = stage;
        this.c = c;
        this.active = active;
        this.checkbox.setSelected(this.active);
        this.minutes = String.valueOf(minutes);
        this.time.getEditor().setText(String.valueOf(minutes));
        this.time.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.5, 10.0, minutes, 0.5));
        if(this.active){
            this.timelabel.setId(null);
            this.time.getEditor().setId(null);
        }
        else{
            this.timelabel.setId("inactivelabel");
            this.time.getEditor().setId("inactivespinner");
        }
        time.valueProperty().addListener((observable, oldValue, newValue) -> {
                if(this.minutes.equals(String.valueOf(newValue)) && !this.changedstate){
                    this.apply.setId("inactivebutton");
                }
                else {
                    this.apply.setId(null);
                }
        });
    }
}
