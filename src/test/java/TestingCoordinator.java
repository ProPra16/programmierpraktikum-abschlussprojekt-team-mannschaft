package test.java;
import main.java.tddt.Coordinator;
import static org.junit.Assert.*;
import org.junit.*;
/**
 * Created by renato on 30.06.16.
 */
public class TestingCoordinator {
    @Test
    public void vonphase1zu2Test(){ //testet, ob die phasen richtig gewechselt werden, hier von  ohase 1 zu 2
        Coordinator coor = new Coordinator("Testklasse", "Testtest");
        //für die Bedingung muss es fehlschlagende Tests geben
        String klassencontent = "public class Testklasse{ static int ret2(){return 1;}}";
        String testcontent = "public class Testest{ @Test public void testeret2(){ assertEquals(Testklasse.ret2(), 2)}}";
        coor.compile(klassencontent, testcontent);
        assertEquals(coor.phase, 2); //sollte danach dann 2 sein
    }

}
