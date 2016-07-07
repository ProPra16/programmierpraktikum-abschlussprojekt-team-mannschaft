package main.java.tddt.data;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.JAXBContext;
import java.io.File;

/**
 * Created by Philipp on 30.06.2016.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)

public class Exercise {

    private String title;
    private String description;
    private String classtext;
    private String testtext;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
        classtext = "public class " + title + "{\n\n\n}";
        testtext = "import static org.junit.Assert.*;\nimport org.junit.*;\n\npublic class " + title + "Test {\n\n\t@Test\n\tpublic void firsttest(){\n\t\tassertEquals(1,2);\n\t}\n}";
    }




    public static void createExercise(String title, String description, File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(Exercise.class);
        Marshaller marshaller = creation.createMarshaller();
        marshaller.marshal(new Exercise(title, description),new File(file + File.separator, title));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getClasstext() {
        return classtext;
    }

    public String getTesttext() {
        return testtext;
    }

    public static Exercise getExercise(File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(Exercise.class);
        Unmarshaller unmarshaller = creation.createUnmarshaller();
        Exercise exercise = (Exercise) unmarshaller.unmarshal(file);

        return exercise;
    }

}
