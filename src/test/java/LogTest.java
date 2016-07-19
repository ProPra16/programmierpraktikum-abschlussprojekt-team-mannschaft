


import main.java.tddt.data.Log;
import main.java.tddt.data.LogList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by samehm on 30.06.2016.
 */

public class LogTest {

    private JAXBContext context ;

    @Before public void init() throws JAXBException{
        this.context = JAXBContext.newInstance(Log.class);
    }

    @Test
    public void TestText () throws JAXBException {




        String time = "2016-07-04-16-12";
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, timeFormat);

        String timer = "2016-06-28-17-53";
        DateTimeFormatter timerFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        LocalDateTime dateTimer = LocalDateTime.parse(timer, timerFormat);

        File file = new File(System.getProperty("user.home"), File.separator + "Logs");
        file.mkdirs();

        Log log = new Log(2,dateTime,dateTimer,"this is a class text ","this is a testtext","this is a compiler Message ");
        LogList loglist = new LogList(file);

        loglist.addLog(log);

        System.out.println(loglist.getLog(0).getTime());

    }


    @Test //getPhase
    public void firstTest() {
        int phase = 1;
        LocalDateTime time = LocalDateTime.now();
        Log lg = new Log(phase,time,time, "Classtext", "Testtext", "Compilemessage");
        Assert.assertTrue(lg.getPhase() == phase);
    }

    @Test //getClasstext
    public void secoundTest() {
        int phase = 1;
        LocalDateTime time = LocalDateTime.now();
        Log lg = new Log(phase,time,time, "Classtext", "Testtext", "Compilemessage");
        Assert.assertTrue(lg.getClassText().equals("Classtext"));
    }

    @Test //getTesttext
    public void thirdTest() {
        int phase = 1;
        LocalDateTime time = LocalDateTime.now();
        Log lg = new Log(phase,time,time, "Classtext", "Testtext", "Compilemessage");
        Assert.assertTrue(lg.getTestText().equals("Testtext"));
    }





}
