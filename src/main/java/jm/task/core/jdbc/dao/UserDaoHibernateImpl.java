package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private User user = new User();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE `new_schema`.`users`(\n" +
                "" + " `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "" + " `name` VARCHAR(45) NOT NULL,\n" +
                "" + " `lastname` VARCHAR(45) NOT NULL,\n" +
                "" + " `age` INT NOT NULL,\n" + " PRIMARY KEY (`id`))\n" +
                "" + "ENGINE = InnoDB\n" + "DEFAULT CHARACTER SET = utf8;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS new_schema.users").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем - " + name + " добавлен в базу данныых");
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {

        List<User> list;
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        list = (List<User>) session.createQuery("From " + User.class.getSimpleName()).list();
        session.getTransaction().commit();
        for (User user : list) {
            System.out.println(user);
        }
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}