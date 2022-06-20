package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement (name = "storage")
@XmlAccessorType (XmlAccessType.FIELD)
public class Storage {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private int capacity;

    @XmlAttribute
    private boolean isFull;
    private Contact phone;

    @XmlElementWrapper (nillable = true)
    @XmlElement (name = "tool")
    private String[] tools;

    public Storage() {
    }

    public Storage(String type, int capacity, boolean isFull, Contact phone, String... tools) {
        this.type = type;
        this.capacity = capacity;
        this.isFull = isFull;
        this.phone = phone;
        this.tools = tools;
    }

    @Override
    public String toString() {
        return "Storage{"
                + "type='" + type + '\''
                + ", capacity=" + capacity
                + ", isFull=" + isFull
                + ", phone=" + phone
                + ", tools=" + Arrays.toString(tools)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        Storage storage = new Storage("building tools", 1000, false,
                new Contact("111-333"), "Hammer", "Saw", "Shovel");
        JAXBContext context = JAXBContext.newInstance(Storage.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(storage, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Storage result = (Storage) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
