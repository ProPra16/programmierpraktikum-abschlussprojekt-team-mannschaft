package main.java.tddt.data;
import java.time.LocalDateTime;

/**
 * Created by samehm on 26.06.2016.
 */
public class Log  { // ---> Zustand der Arbeit des Users (es wird gespeichert was gerade vom User geänder wurde)
   private  int phase;
    private LocalDateTime  time;
    private  LocalDateTime timer; // Hier sind alle Instanzvariablen der Klasse
    private  String  classText;
    private String testText;
    private String compileMessage;

        public Log (int phase, LocalDateTime time, LocalDateTime timer, String classText, String testText, String compileMessage){
            this.phase = phase;
            this.time = time;
            this.timer = timer;
            this.classText = classText ;
            this.testText = testText ;
            this.compileMessage= compileMessage;
        }
    public int getPhase(){
        return this.phase;
    }

    public LocalDateTime getTime() {
        return this.time;
    }
    public LocalDateTime getTimer() {
        return this.timer;
    }
    public String getClassText(){
        return this.classText;
    }

    public String getTestText(){
        return this.testText;
    }
    public String getCompileMessage(){
        return this.compileMessage;
    }


}
