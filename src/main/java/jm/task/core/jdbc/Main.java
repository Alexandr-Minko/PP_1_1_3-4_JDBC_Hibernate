package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Вася", "Иванов", (byte) 15);
        userService.saveUser("Коля", "Петров", (byte) 16);
        userService.saveUser("Маша", "Сидорова", (byte) 17);
        userService.saveUser("Даша", "Пугачева", (byte) 18);
        for (User user : userService.getAllUsers()){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
