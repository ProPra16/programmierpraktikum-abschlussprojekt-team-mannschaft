package main.java.tddt.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class LogList {

    // Liste mit allen logs
    List<Log> logs = new ArrayList<>();

    // Pfad zu exercises-Ordner wo alle XML Datein gesaved werden
    public static String directoryPath = LogList.class.getResource("exercices/").toExternalForm();

    // Konstruktor
    // geht zu Ordner-exercises und sucht alle Datei mit XML
    // und fuegt dann alle in die obere Liste
    public LogList() {
        // oeffne exercises-Ordner
        File directory = new File(directoryPath);

        // suche alle Datein mit Endung XML und save Dateinamen in Array
        String[] allLogFiles = directory.list((File dir, String name) -> name.endsWith(".xml"));

        // oeffne alle gefundenen Datein und lese XML und fuege in logs Liste hinzu
        Log log = null;
        JAXBContext creation = null;
        for (String logFile : allLogFiles) {
            try {
                creation = JAXBContext.newInstance(Log.class);
                Unmarshaller unmarshaller = creation.createUnmarshaller();
                log = (Log) unmarshaller.unmarshal(getClass().getResource("/exercises/" + logFile));
                logs.add(log);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    // fuege log in Liste und erstelle neue log-XML Datei
    public void addLog(Log log) throws JAXBException {
        logs.add(log);
        Log.createLog(log.getPhase(), log.getTime(), log.getTimer(), log.getClassText(), log.getTestText(), log.getCompileMessage(),new File(directoryPath));
    }

    // delete alle Logs aus logs-Liste und alle Datein aus exercises-Ordner
    public void deleteAll() {
        for (Log log : logs) {
            deleteLast();
        }
    }

    // delete letzte Log aus logs-List und aus exercises-Ordner
    public void deleteLast() {
        // speichere Name von letzten Log aus der logs-List
        String fileNameLastLog = logs.get(logs.size() ).getTime().toString();

        // delete letzte Log aus der logs-Liste
        logs.remove(logs.size() );

        // oeffne letzen Log-Datei aus exercises-Ordner
        File fileLastLog = new File(directoryPath + fileNameLastLog);

        // Delete letzte Log-Datei und gibt Meldung aus
        if (fileLastLog.delete() == true) System.out.println(fileLastLog.getName() + "wurde deleted");
        else {
            System.out.println("Fehler beim Log-deleten");
        }
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public Log getLog(int index) {
        return logs.get(index);
    }

    public List<Log> getLogs() {
        return logs;

    }
}