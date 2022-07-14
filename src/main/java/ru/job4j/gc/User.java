package ru.job4j.gc;

public class User {
    private String name;
    private int age;
    private int id;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        //super.finalize();
        System.out.printf("Removed %s %d %d%n", name, age, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
