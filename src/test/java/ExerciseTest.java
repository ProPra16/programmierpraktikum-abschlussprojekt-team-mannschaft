package test.java;

import main.java.tddt.data.Exercise;
import org.junit.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by Philipp on 06.07.2016.
 */
public class ExerciseTest {

    private JAXBContext test;

    @Before public void init() throws JAXBException {
        this.test = JAXBContext.newInstance(Exercise.class);
    }

    @Test
    public void TestCreate() throws JAXBException {

        String title = "Roman Numbers";
        String description = "Wandel arabische Ziffern in römische Ziffern um.";
        File file = new File(System.getProperty("user.dir"));

        Exercise.createExercise(title, description, file);
    }

    @Test //Title
    public void exTestOne(){
        String title = "Titel";
        String description = "Description";
        Exercise ex = new Exercise(title,description);
        Assert.assertTrue(ex.getTitle().equals(title));

    }

    @Test //description
    public void exTestTwo(){
        String title = "Titel";
        String description = "Description";
        Exercise ex = new Exercise(title,description);
        Assert.assertTrue(ex.getDescription().equals(description));

    }









}
