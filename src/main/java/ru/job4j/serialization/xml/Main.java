package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /* Get the context for accessing the API */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /* Create serializator */
        Marshaller marshaller = context.createMarshaller();
        /* Point that formatting needed */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Serialization */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Create deserializator */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* Deserialization */
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
