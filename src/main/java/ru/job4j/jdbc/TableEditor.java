package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    private void execute(String sql, String tableName) throws Exception {
        try (Statement state = connection.createStatement()) {
            state.execute(sql);
            if (!sql.startsWith(String.format("drop table %s", tableName))) {
            System.out.println(getTableScheme(connection, tableName));
            }
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("create table %s (id serial primary key)", tableName);
        execute(sql, tableName);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table %s;", tableName);
        execute(sql, tableName);

    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("alter table %s add column %s %s;", tableName, columnName, type);
        execute(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("alter table %s drop %s;", tableName, columnName);
        execute(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        execute(sql, tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("idea_db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor editor = new TableEditor(properties)) {
            String tableName = "tab1";
            String columnName = "col";
            String newColumnName = "newcol";
            String type = "varchar(255)";
            editor.createTable(tableName);
            editor.addColumn(tableName, columnName, type);
            editor.renameColumn(tableName, columnName, newColumnName);
            editor.dropColumn(tableName, newColumnName);
            editor.dropTable(tableName);
        }
    }
}
