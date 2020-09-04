package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util  implements UserService {
   private User user = new User();
    List<User> users = new ArrayList<>();

    public void createUsersTable()  {
        Statement statement;
        String sqlCommand = "CREATE TABLE `new_schema`.`user`(\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8;";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            dropUsersTable();
            createUsersTable();
        }

    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE user;";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            createUsersTable();
            dropUsersTable();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO user(Name, LastName, AGE) VALUES(?,?,?) ";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            user.setAge(age);
            user.setName(name);
            user.setLastName(lastName);
            System.out.println("User с именем – "+name +" добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM user WHERE ID = ?");
        preparedStatement.setLong(1,id);
        preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM user");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            user.setId( resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setAge(resultSet.getByte(4));
            users.add(user);
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("TRUNCATE TABLE  user;");
        preparedStatement.executeUpdate();

    }
}
