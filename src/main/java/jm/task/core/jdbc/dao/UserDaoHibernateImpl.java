package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getHibernateSessionFactory();
    }

    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (IllegalStateException ex) {
                System.out.println("Создание таблицы. Отмена изменений: Ошибка");
            }
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE user").executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (IllegalStateException ex) {
                System.out.println("Удаление таблицы. Отмена изменений: Ошибка");
            }
        } finally {
            if (session != null) {session.close();}
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(new User(name,lastName, age));
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (IllegalStateException ex) {
                System.out.println("Добавить user-a по id. Отмена изменений: Ошибка");
            }
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete User where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (IllegalStateException ex) {
                System.out.println("Удалить user-a по id. Отмена изменений: Ошибка");
            }
        } finally {
            if (session != null) {session.close();}
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            listUsers = (List<User>) session.createQuery("From User").list();
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (IllegalStateException ex) {
                System.out.println("Получить всех user-ов. Отмена изменений: Ошибка");
            }
        } finally {
            if (session != null) {session.close();}
        }
        return Objects.requireNonNullElseGet(listUsers, ArrayList::new);
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE user").executeUpdate();
            session.createSQLQuery("CREATE TABLE user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age TINYINT)\"").executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
            try {
                transaction.rollback();
            } catch (IllegalStateException ex) {
                System.out.println("Очистка таблицы. Отмена изменений: Ошибка");
            }
        } finally {
            if (session != null) {session.close();}
        }
    }
}
