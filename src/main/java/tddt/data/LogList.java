package main.java.tddt.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogList {

    // Liste mit allen logs
    private List<Log> logs = new ArrayList<>();

    // Pfad zu exercises-Ordner wo alle XML Datein gesaved werden
    public static File directoryPath;

    // Konstruktor
    // geht zu Ordner-exercises und sucht alle Datei mit XML
    // und fuegt dann alle in die obere Liste
    public LogList(File directory) {
        directoryPath = directory;
        // suche alle Datein mit Endung XML und save Dateinamen in Array
        File[] allLogFiles = directory.listFiles();

        // oeffne alle gefundenen Datein und lese XML und fuege in logs Liste hinzu
        Log log = null;
        JAXBContext creation = null;
        for (File logFile : allLogFiles) {
            try {
                creation = JAXBContext.newInstance(Log.class);
                Unmarshaller unmarshaller = creation.createUnmarshaller();
                log = (Log) unmarshaller.unmarshal(logFile);
                logs.add(log);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    // fuege log in Liste und erstelle neue log-XML Datei
    public void addLog(Log log) throws JAXBException {
        logs.add(log);
        Log.createLog(log.getPhase(), log.getTime(), log.getTimer(), log.getClassText(), log.getTestText(), log.getCompileMessage(),directoryPath);
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
        String fileNameLastLog = logs.get(logs.size()-1 ).getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-DD-HH-mm-ss"));

        // delete letzte Log aus der logs-Liste
        logs.remove(logs.size()-1);

        // oeffne letzen Log-Datei aus exercises-Ordner
        File fileLastLog = new File(directoryPath, File.separator + fileNameLastLog);

    }

    public Log getLog(int index) {
        return logs.get(index);
    }

    public int size(){
        return logs.size();
    }
}