package ecse321.group12.tamas.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ecse321.group12.tamas.model.*;

public abstract class PersistenceXStream {

    private static XStream xstream = new XStream();
    private static String filename = "data.xml";

    public static ResourceManager initializeModelManager(String fileName) {
        // Initialization for persistence
        ResourceManager rm;
        setFilename(fileName);
        setAlias("applicant", Applicant.class);
        setAlias("application", Application.class);
        setAlias("assignment", Assignment.class);
        setAlias("course", Course.class);
        setAlias("department", Department.class);
        setAlias("graderJob", GraderJob.class);
        setAlias("instructor", Instructor.class);
        setAlias("job", Job.class);
        setAlias("manager", ResourceManager.class);
        setAlias("TAjob", TAjob.class);
        setAlias("user", User.class);

        // load model if exists, create otherwise
        File file = new File(fileName);
        if (file.exists()) {
            rm = (ResourceManager) loadFromXMLwithXStream();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            rm = ResourceManager.getInstance();
            saveToXMLwithXStream(rm);
        }
        return rm;

    }

    public static boolean saveToXMLwithXStream(Object obj) {
        xstream.setMode(XStream.ID_REFERENCES);
        String xml = xstream.toXML(obj); // save our xml file

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(xml);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Object loadFromXMLwithXStream() {
        xstream.setMode(XStream.ID_REFERENCES);
        try {
            FileReader fileReader = new FileReader(filename); // load our xml file
            return xstream.fromXML(fileReader);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setAlias(String xmlTagName, Class<?> className) {
        xstream.alias(xmlTagName, className);
    }

    public static void setFilename(String fn) {
        filename = fn;
    }

}