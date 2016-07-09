package main.java.tddt.data;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.tddt.Coordinator;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;

public class Timer {
    private LocalDateTime time ;
    private long elapsedSeconds;
    private LocalDateTime now;
    private Label clock;
    private Coordinator c;
    private AnimationTimer timer;
    private LocalDateTime babystepstime;

    public Timer(Label clock){
        this(clock,LocalDateTime.now().withMinute(0).withSecond(0));
    }
    public Timer(Label clock,LocalDateTime start){
        this.time=start;
        this.clock=clock;
        this.clock.setId("clock");
        this.now= LocalDateTime.now();
        this.start();
    }
    public Timer(Label clock, Coordinator c,LocalDateTime start){
        this.clock=clock;
        this.c=c;
        this.now= LocalDateTime.now();
        this.clock.setId("clock");
        this.babystepstime=start;
        this.time = LocalDateTime.now().withMinute(0).withSecond(0);////!
        this.start();

    }
    public LocalDateTime stop(){
        timer.stop();

        return this.time;
    }


     private void start() {
         this.timer = new AnimationTimer() {

                public void handle(long i) {
                    elapsedSeconds = Duration.between(now.plusMinutes(time.getMinute()).plusSeconds(time.getSecond()) , LocalDateTime.now()).getSeconds();
                    time.plusMinutes(elapsedSeconds/60);
                    time.plusSeconds(elapsedSeconds % 60) ;

                    if(c==null){
                        clock.setText(time.getMinute() + ":" + time.getSecond());
                    }else{
                        elapsedSeconds = Duration.between(now.plusMinutes(time.getMinute()).plusSeconds(time.getSecond()) , LocalDateTime.now()).getSeconds();
                        babystepstime.minusMinutes(elapsedSeconds/60);
                        babystepstime.minusSeconds(elapsedSeconds % 60) ;
                        
                    }
                    if(babystepstime.getMinute()==0&&babystepstime.getSecond()<=30){
                        clock.setId("clockred");
                    }
                    if(babystepstime.getMinute()==0&&babystepstime.getSecond()<=5){
                        Toolkit.getDefaultToolkit().beep();
                    }
                    if(babystepstime.getMinute()==0&&babystepstime.getSecond()<=0){
                        c.Babystepsover();
                    }
                }


                public void start() {
                    super.start();
                }

                public void stop() {
                    super.stop();
                }

            };


    }

