package test.java;

import javafx.scene.control.Label;
import main.java.tddt.Coordinator;
import main.java.tddt.data.Timer;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

public class TimerTest{

    private JAXBContext test;
    /*@Before
    public void init() throws JAXBException {
        this.test = JAXBContext.newInstance(Timer.class);
    }*/

    @Test //teste die stop() methode
    public void firstTest() {
        Label time = new Label();
        Coordinator c = new Coordinator("","");
        LocalDateTime ldt = LocalDateTime.now();
        Timer timer = new Timer(time,c , ldt);

        Assert.assertTrue(timer.stop().equals(ldt.getMinute() + ldt.getSecond()));
    }

    @Test //test wird etwas zurück gegeben?
    public void secondTest() {
        Label time = new Label();
        Coordinator c = new Coordinator("","");
        LocalDateTime ldt = LocalDateTime.now();
        Timer timer = new Timer(time,c , ldt);
        String t = new String("00:00");

        assertNotNull(t, timer.stop());
    }





    /*
    // es gibt natuerlich noch mehr Assertions, mit den hier genannten kann man aber schonmal viel anfangen
    @Test
    public void thirdTest() {
        Timer timer = new Timer(Label time, Coordinator c);

        // assertTrue, um einen Ausdruck auszuwerten welcher TRUE zurückgeben soll
        assertTrue(60 == exampleUnit.doSomething(55));

        assertTrue("5".equals(exampleUnit.doSomethingElse()));

        // analog dazu assertFalse
        assertFalse(213 == exampleUnit.doSomething(12));


    }
    @Test
    public void fourthTest() {
        Timer timer = new Timer(Label time, Coordinator c);

        // assertTrue, um einen Ausdruck auszuwerten welcher TRUE zurückgeben soll
        assertTrue(60 == exampleUnit.doSomething(55));

        assertTrue("5".equals(exampleUnit.doSomethingElse()));

        // analog dazu assertFalse
        assertFalse(213 == exampleUnit.doSomething(12));


    }
    @Test
    public void fifthTest() {
        Timer timer = new Timer(Label time, Coordinator c);

        // assertTrue, um einen Ausdruck auszuwerten welcher TRUE zurückgeben soll
        assertTrue(60 == exampleUnit.doSomething(55));

        assertTrue("5".equals(exampleUnit.doSomethingElse()));

        // analog dazu assertFalse
        assertFalse(213 == exampleUnit.doSomething(12));


    }*/

}