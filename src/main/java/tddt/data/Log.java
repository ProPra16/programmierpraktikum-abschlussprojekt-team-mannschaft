package main.java.tddt.data;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name="log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log  { // ---> Zustand der Arbeit des Users (es wird gespeichert was gerade vom User geänder wurde)

    private  int phase;
    private String  time;
    private  String timer; // Hier sind alle Instanzvariablen der Klasse
    private  String  classText;
    private String testText;
    private String compileMessage;

    public Log() {

    }

    public Log (int phase, LocalDateTime time, LocalDateTime timer, String classText, String testText, String compileMessage){
        this.phase = phase;
        this.setTime(time);
        this.setTimer(timer);
        this.classText = classText ;
        this.testText = testText ;
        this.compileMessage= compileMessage;
    }
    public int getPhase(){
        return this.phase;
    }

    public LocalDateTime getTime() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, timeFormat);

        return dateTime;

    }

    public LocalDateTime getTimer() {
        DateTimeFormatter timerFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimer = LocalDateTime.parse(timer, timerFormat);

        return dateTimer;
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


    public void setPhase(int phase){
        this.phase=phase;
    }

    public void setTime(LocalDateTime time) {

        this.time = time.toString().replaceAll("T"," ");
    }

    public void setTimer(LocalDateTime timer) {
        this.timer = timer.toString().replaceAll("T"," ");
    }

    public void setClassText(String classText){
        this.classText= testText;
    }
    public void setTestText(String testText){
        this.testText = testText;
    }
    public void setCompileMessage(String compileMessage){
        this.compileMessage = compileMessage;
    }
}
