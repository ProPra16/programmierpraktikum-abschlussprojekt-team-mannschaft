package main.java.tddt.data;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import main.java.tddt.Coordinator;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Timer {

	private Timeline timer;
	private LocalDateTime start;
    private LocalDateTime babystart;
	private Label time;
    private Coordinator c;
    private boolean babysteps;

	public Timer(Label time, Coordinator c){
		this.time = time;
        this.c = c;
        this.babysteps = false;
        this.time.setId("clock");
		this.start();
	}

    public Timer(Label time, Coordinator c, LocalDateTime start){
        this.time = time;
        this.start = start;
        this.c = c;
        this.babysteps = false;
        this.time.setId("clock");
        this.start();
    }

    public Timer(Label time, Coordinator c, double start){
        this.time = time;
		int minute = (int) (start);
		int second = (int)((start - (int) start)*60);
        this.babystart = LocalDateTime.now().withMinute(minute).withSecond(second);
        this.c = c;
        this.babysteps = true;
        this.time.setId("clock");
        this.start();
    }

	public void start(){
		if(this.start == null) {
			start = LocalDateTime.now();
		}
		else{
			start = LocalDateTime.now().minusMinutes(this.start.getMinute()).minusSeconds(this.start.getSecond());
		}
		timer = new Timeline(
				new KeyFrame(Duration.seconds(0), event -> {
                    LocalDateTime actTime = LocalDateTime.now().minusMinutes(start.getMinute()).minusSeconds(start.getSecond());
                    if(actTime.getMinute() == 59 && actTime.getSecond() == 59){
                        c.Babystepsover();
                    }
                    if(!this.babysteps) {
                        time.setText(actTime.format(DateTimeFormatter.ofPattern("mm:ss")));
                    }
                    else{
                        LocalDateTime babytimer = babystart.minusMinutes(actTime.getMinute()).minusSeconds(actTime.getSecond());
                        if(babytimer.getMinute() == 0){
                            // 29 und 30 damit auch beim überspringen einer sekunde die uhr umgestellt wird
                            // dieser Fall ist extrem unwahrscheinlich, aber möglich
                            if(babytimer.getSecond() == 30 || babytimer.getSecond() == 29) {
                                time.setId("clockred");
                            }
                            else if(babytimer.getSecond() <= 5){
                                Toolkit.getDefaultToolkit().beep();
								if(babytimer.getSecond() == 0){
									c.Babystepsover();
								}
                            }
                        }
                        // auch 59 damit beim überspringen der zeit 00:00 trotzdem beendet wird
                        // dieser fall ist extrem unwahrscheinlich, aber möglich
                        else if(babytimer.getMinute() == 59){
                            c.Babystepsover();
                        }
                        time.setText(babytimer.format(DateTimeFormatter.ofPattern("mm:ss")));
                    }
				}),
				new KeyFrame(Duration.seconds(1))
		);
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
	}

	public LocalDateTime stop(){
		timer.stop();
		return LocalDateTime.now().minusMinutes(start.getMinute()).minusSeconds(start.getSecond());
	}
}

