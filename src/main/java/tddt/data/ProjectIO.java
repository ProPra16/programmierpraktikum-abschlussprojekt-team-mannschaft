package main.java.tddt.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;

/**
 * Created by Philipp on 07.07.2016.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder = {"title", "description", "classtesxt", "testext", "phase", "babystepsactive", "babymins"})

public class ProjectIO {

    private String title;
    private String description;
    private String classtext;
    private String testtext;
    private int phase;
    private boolean babystepsactive;
    private double babymins;

    private ProjectIO(String title, String description, String classtext, String testtext, int phase, boolean babystepsactive, double babymins) {
        this.title = title;
        this.description = description;
        this.classtext = classtext;
        this.testtext = testtext;
        this.phase = phase;
        this.babystepsactive = babystepsactive;
        this.babymins = babymins;
    }

    public static void saveProjectIO(String title, String description, String classtext, String testtext, int phase, boolean babystepsactive, double babymins, File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(ProjectIO.class);
        Marshaller marshaller = creation.createMarshaller();
        marshaller.marshal(new ProjectIO(title, description, classtext, testtext, phase, babystepsactive, babymins), new File(file + File.separator, title));
    }

    public String getTitle() { return title;}

    public String getDescription() { return description;}

    public String getClasstext() { return classtext;}

    public String getTesttext() { return testtext;}

    public int getPhase() { return phase;}

    public boolean getBabystepsactive() { return babystepsactive;}

    public double getBabymins() { return babymins;}

    public static ProjectIO getProjectIO(File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(ProjectIO.class);
        Unmarshaller unmarshaller = creation.createUnmarshaller();
        ProjectIO projectIO = (ProjectIO) unmarshaller.unmarshal(file);

        return projectIO;
    }

}
