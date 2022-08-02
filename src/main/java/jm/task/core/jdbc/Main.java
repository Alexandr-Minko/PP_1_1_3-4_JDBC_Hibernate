package jm.task.core.jdbc;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("jm.task.core.jdbc.util.TestClass2");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}
