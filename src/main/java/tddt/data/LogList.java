package main.java.tddt.data;




import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name="logList")
@XmlAccessorType (XmlAccessType.FIELD)
public class LogList {

    @XmlElement(name = "log")
    private List<Log> logs = new ArrayList<Log>();


    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public void addLog(Log log){
        logs.add(log);
        saveLoglist();
    }

    public void delete(){
        logs.clear();
        saveLoglist();
    }

    public Log getLog(int i){
        return logs.get(i);
    }


    public void loadLoglist(){
        try {
            JAXBContext context = JAXBContext.newInstance(LogList.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            LogList loglist = (LogList) unmarshaller.unmarshal(new File("Log.xml"));
            this.logs = loglist.getLogs();
        }catch(JAXBException e) {
            e.printStackTrace();
        }
    }




    public void saveLoglist(){
        try {
            JAXBContext context = JAXBContext.newInstance(LogList.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(this,new File("Log.xml"));

        }catch(JAXBException e){
            e.printStackTrace();
        }
    }
}
