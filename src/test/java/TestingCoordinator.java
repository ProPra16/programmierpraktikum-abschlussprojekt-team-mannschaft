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
        Coordinator coor = new Coordinator("Bar", "BarTest");
        //für die Bedingung muss es fehlschlagende Tests geben
        //Testklassenstrings aus den Tests der Biblithek entnommen
        String klassencontent = "public class Bar { \n"
                + " public static int square(int n) { \n"
                + "    return n * n; \n"
                + " }\n"
                + " public static int plusThree(int n) { \n"
                + "    return 3 + n; \n"
                + " }\n"
                + "}";
        String testcontent = "import static org.junit.Assert.*;\n"
                + "import org.junit.Test;\n"
                + "public class BarTest { \n"
                + "   @Test\n"
                + "   public void squareOf2_shouldReturn16() { \n "
                + "       assertEquals(16, Bar.square(2)); \n"
                + "   }\n "
                + "}";
        String testcompresults = coor.compile(klassencontent, testcontent);
        assertEquals(2, coor.phase); //sollte danach dann 2 sein
    }

    @Test
    public void vonphase2zu3Test(){ //testet ob bei erfolgreichen Tests in phase 2 zu phase 3 übergegangen wird
        Coordinator coortest = new Coordinator("Bar", "BarTest");
        //Testklassenstrings aus der Compile Bibiltohek
        String klassencontentfalsch = "public class Bar { \n"
                + " public static int square(int n) { \n"
                + "    return n * n; \n"
                + " }\n"
                + " public static int plusThree(int n) { \n"
                + "    return 3 + n; \n"
                + " }\n"
                + "}";
        String testcontentfalsch = "import static org.junit.Assert.*;\n"
                + "import org.junit.Test;\n"
                + "public class BarTest { \n"
                + "   @Test\n"
                + "   public void squareOf2_shouldReturn16() { \n "
                + "       assertEquals(16, Bar.square(2)); \n"
                + "   }\n "
                + "}";
        String failetestresults = coortest.compile(klassencontentfalsch, testcontentfalsch);
        //phase sollte hiernach 2 sein
        //Testklassenstrings aus den Tests der Biblithek entnommen
        String klassencontent = "public class Bar { \n"
                + " public static int square(int n) { \n"
                + "    return n * n; \n"
                + " }\n"
                + " public static int plusThree(int n) { \n"
                + "    return 3 + n; \n"
                + " }\n"
                + "}";
        String testcontent = "import static org.junit.Assert.*;\n"
                + "import org.junit.Test;\n"
                + "public class BarTest { \n"
                + "   @Test\n"
                + "   public void squareOf3_shouldReturn9() { \n "
                + "       assertEquals(9, Bar.square(3)); \n"
                + "   }\n "
                + "}";
        String testcompresults = coortest.compile(klassencontent, testcontent);
        assertEquals(3, coortest.phase); //phase sollte danach dann 3 sein
    }

}
