package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private Store store;

    private Marshaller marshaller;

    private JAXBContext context;

    public XMLReport(Store store) throws JAXBException {
        context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result = "";
        Employees employees = new Employees(store.findBy(filter));
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
