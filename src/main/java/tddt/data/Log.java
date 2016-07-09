package main.java.tddt.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ---> Zustand der Arbeit des Users (es wird gespeichert was gerade vom User geänder wurde)
@XmlRootElement(name="log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log  {

    private int phase;
    private String time;
    private String timer;
    private String classText;
    private String testText;
    private String compileMessage;

    // Konstruktor
    public Log (int phase, LocalDateTime time, LocalDateTime timer,
                String classText, String testText, String compileMessage) {
        this.phase = phase;

        // setze Time und Timer

        this.setTime(time);
        this.setTimer(timer);

        this.classText = classText;
        this.testText = testText;
        this.compileMessage= compileMessage;
    }

    // erstelle Log-XML Datei
    public static void createLog(int phase, LocalDateTime time, LocalDateTime timer,
                                 String classText, String testText, String compileMessage,
                                 File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(Log.class);
        Marshaller marshaller = creation.createMarshaller();

        // Erstelle XML Datei und Dateiname ist Datum und Uhr
        marshaller.marshal(new Log(phase, time, timer, classText, testText, compileMessage), new File(file + File.separator, time.toString()));
    }

    public void setPhase(int phase) { this.phase=phase; }
    public void setTime(LocalDateTime time) { this.time = time.toString(); }
    public void setTimer(LocalDateTime timer) { this.timer = timer.toString(); }
    public void setClassText(String classText) { this.classText= testText;}
    public void setTestText(String testText) { this.testText = testText; }
    public void setCompileMessage(String compileMessage) { this.compileMessage = compileMessage; }


    // lesen Log-XML Datei
    public static Log getLog(File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(Log.class);
        Unmarshaller unmarshaller = creation.createUnmarshaller();
        return (Log) unmarshaller.unmarshal(file);
    }

    public LocalDateTime getTime() {
        // z.B. 2016-07-04T16:30:42.576
        // jahr-monat-tagTstunden:minute:sekunde.milisekunde
        String str ="2016-07-04T16:30:42.576";
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss.zzz");
        return LocalDateTime.parse(str, timeFormat);

    }

    public LocalDateTime getTimer() {
        DateTimeFormatter timerFormat = DateTimeFormatter.ofPattern("HH-mm-ss");
        return LocalDateTime.parse(timer, timerFormat);
    }

    public int getPhase() { return this.phase; }
    public  String getClassText() { return this.classText; }
    public String getTestText() { return this.testText; }
    public String getCompileMessage() { return this.compileMessage; }
}
