package main.java.tddt.gui.dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;
import main.java.tddt.gui.Controller;


/**
 * Created by Roter Emu on 28.06.2016.
 */
public class BabystepsController {

    private boolean active;
    private boolean changedstate = false;
    private String minutes;
    private Stage stage;
    private Controller c;

    public CheckBox checkbox;
    public Label timelabel;
    public Spinner time;
    public Button apply;

    public void apply(ActionEvent actionEvent) {
        if((this.checkbox.isSelected() && this.changedMins()) || this.changedstate) {
            try{c.babystepsOutput(this.checkbox.isSelected(), Double.parseDouble(this.minutes));}
            catch(Exception e){}
            this.stage.close();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        this.stage.close();
    }

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

    private boolean changedMins() {
        try {
            return !(Double.parseDouble(this.minutes) == Double.parseDouble(this.time.getEditor().getText()));
        }
        catch(Exception e){ return false; }
    }

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
