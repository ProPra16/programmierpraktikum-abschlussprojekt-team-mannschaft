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
    private String className;
    private String testName;
    private JAXBContext test;

    public Exercise(String title, String description, String className, String testName) {
        this.title = title;
        this.description = description;
        this.className = className;
        this.testName = testName;
    }

    public void createExercise(String title, String description) throws JAXBException {
        this.test = JAXBContext.newInstance(Exercise.class);
        Marshaller marshaller = this.test.createMarshaller();
        marshaller.marshal(new Exercise(title, description, title, title+"Test"),new File(title+".xml"));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getClassName() {
        return className;
    }

    public String getTestName() {
        return testName;
    }

    public Exercise getExercise(String filepath) throws JAXBException {
        this.test = JAXBContext.newInstance(Exercise.class);
        Unmarshaller unmarshaller = this.test.createUnmarshaller();
        Exercise exercise = (Exercise) unmarshaller.unmarshal(new File(test+".xml"));

        return exercise;
    }

}
