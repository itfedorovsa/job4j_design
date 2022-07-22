package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

public class ReportEngineProgrammists implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportEngineProgrammists(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html>")
                .append(System.lineSeparator())
                .append("   <head>Name; Hired; Fired; Salary;</head>")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("   <body>")
                    .append(employee.getName()).append("; ")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append("; ")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append("; ")
                    .append(employee.getSalary()).append("; ")
                    .append("</body>")
                    .append(System.lineSeparator())
                    .append("</html>");
        }
        return text.toString();
    }

    public static void main(String[] args) {
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
        System.out.println(expect);
    }

}
