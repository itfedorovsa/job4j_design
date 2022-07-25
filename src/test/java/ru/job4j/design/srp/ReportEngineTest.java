package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("John", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append("; ")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append("; ")
                .append(worker.getSalary()).append("; ")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenNewForProgrammists() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("John", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineProgrammists(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>")
                .append(System.lineSeparator())
                .append("   <head>Name; Hired; Fired; Salary;</head>")
                .append(System.lineSeparator())
                .append("   <body>")
                .append(worker.getName()).append("; ")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append("; ")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append("; ")
                .append(worker.getSalary()).append("; ")
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenNewForAccountants() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("John", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngineAccountants(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary(Euro);")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append("; ")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append("; ")
                .append(worker.getSalary() * ReportEngineAccountants.CONVERT_USD_TO_EUR).append("; ")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenNewForHR() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("John", 100);
        Employee worker2 = new Employee("Jade", 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportEngineHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("; ")
                .append(worker2.getSalary()).append("; ")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("; ")
                .append(worker1.getSalary()).append("; ")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenXMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("John", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employee>\n")
                .append("        <fired>").append(format.format(worker.getFired().getTime())).append("</fired>\n")
                .append("        <hired>").append(format.format(worker.getHired().getTime())).append("</hired>\n")
                .append("        <name>").append(worker.getName()).append("</name>\n")
                .append("        <salary>").append(worker.getSalary()).append("</salary>\n")
                .append("    </employee>\n")
                .append("</employees>\n");
        assertThat(expect.toString(), is(engine.generate(em -> true)));
    }

    @Test
    public void whenJSONReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        Employee worker1 = new Employee("John", now, now, 100);
        Employee worker2 = new Employee("Jade", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("; ")
                .append(format.format(worker1.getHired().getTime())).append("; ")
                .append(format.format(worker1.getFired().getTime())).append("; ")
                .append(worker1.getSalary()).append("; ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("; ")
                .append(format.format(worker2.getHired().getTime())).append("; ")
                .append(format.format(worker2.getFired().getTime())).append("; ")
                .append(worker2.getSalary()).append("; ")
                .append(System.lineSeparator());
        final String report = expect.toString();
        final Gson gson = new GsonBuilder().create();
        String result = gson.toJson(report);
        assertThat(engine.generate(em -> true), is(result));
    }

}