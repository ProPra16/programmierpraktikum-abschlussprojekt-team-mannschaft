package test.java;

import main.java.tddt.data.Log;
import main.java.tddt.data.LogList;
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
        this.context = JAXBContext.newInstance(LogList.class);
    }

    @Test
    public void TestText () throws JAXBException {




        String time = "2018-07-30 08:30";
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, timeFormat);

        String timer = "2018-10-30 08:30";
        DateTimeFormatter timerFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimer = LocalDateTime.parse(timer, timerFormat);

        Log log = new Log(2,dateTime,dateTimer,"this is a class text 3","this is a testtext3","this is a compiler Message 3");
        LogList loglist = new LogList();
        loglist.loadLoglist();
        loglist.addLog(log);

        System.out.println(loglist.getLog(0).getTime());
        loglist.saveLoglist();
    }
}
