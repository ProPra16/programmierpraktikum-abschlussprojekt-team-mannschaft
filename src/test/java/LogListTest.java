package test.java;

/**
 * Created by samehm on 14.07.2016.
 */
//package test.java;

import main.java.tddt.data.LogList;
import main.java.tddt.data.Log;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by samehm on 14.07.16.
 */
public class LogListTest {

    private LogList logList;

    @Before
    public void init() {
        this.logList = new LogList(new File("../../../../resources/exercises/"));
    }

    @Test
    public void addOneLog() {
        int logsSizeBefore = logList.size();
        int logsSizeAfter = 0;
        try {
            int phase = 1;

            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime time = LocalDateTime.parse("2016-07-04-16-12-13", timeFormat);

            DateTimeFormatter timerFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            LocalDateTime timer = LocalDateTime.parse("2016-06-28-17-53-30", timerFormat);

            String classtext = "public class Fakultät{\n\n\n}";
            String testtext = "import static org.junit.Assert.*;\n" +
                    "        import org.junit.*;\n" +
                    "\n" +
                    "        public class FakultätTest {\n" +
                    "\n" +
                    "            @Test\n" +
                    "            public void firsttest(){\n" +
                    "                assertEquals(1,2);\n" +
                    "            }\n" +
                    "        }";

            String compileMessage = "";

            logList.addLog(new Log(phase, time, timer, classtext, testtext, compileMessage));

            logsSizeAfter = logList.size();

            // Test
            assertTrue(logsSizeBefore + 1 == logsSizeAfter);
        }
        catch (Exception e) {}
    }

    @Test
    public void deleteAllLogs() {
        logList.deleteAll();
        int logsSizeAfter = logList.size();

        assertTrue(logsSizeAfter == 0);
    }

    @Test
    public void deleteOnlyLastLog() {
        int logsSizeBefore = logList.size();
        logList.deleteLast();
        int logsSizeAfter = logList.size();

        assertTrue(logsSizeAfter == logsSizeBefore - 1);
    }

}
