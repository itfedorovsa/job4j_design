package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

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
                    .append(employee.getSalary()).append("; ");
        }
        text.append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        return text.toString();
    }

}
