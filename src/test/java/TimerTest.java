package test.java;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import main.java.tddt.Coordinator;
import main.java.tddt.data.Timer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

public class TimerTest{
/*  die Tests funktionieren wohl nur lokal und nicht auf Travis
    private JAXBContext test;
    @Before
    public void init() throws JAXBException {
        this.test = JAXBContext.newInstance(Timer.class);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
        });
    }
    @Test //teste die stop() methode min
    public void firstTest() {
        Label time = new Label();
        Coordinator c = new Coordinator("","");
        LocalDateTime ldt = LocalDateTime.now();
        Timer timer = new Timer(time,c , ldt);

        Assert.assertTrue(timer.stop().getMinute() == ldt.getMinute());
    }

    @Test //test wird etwas zurueck gegeben?
    public void secondTest() {
        Label time = new Label();
        Coordinator c = new Coordinator("","");
        LocalDateTime ldt = LocalDateTime.now();
        Timer timer = new Timer(time,c , ldt);
        String t = new String("00:00");

        assertNotNull(t, timer.stop());
    }

    @Test //teste die stop() methode sec
    public void thirdTest() {
        Label time = new Label();
        Coordinator c = new Coordinator("","");
        LocalDateTime ldt = LocalDateTime.now();
        Timer timer = new Timer(time,c , ldt);

        Assert.assertTrue(timer.stop().getSecond() == ldt.getSecond());
    }
*/
}
