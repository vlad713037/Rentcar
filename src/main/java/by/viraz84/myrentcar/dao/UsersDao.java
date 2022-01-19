package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;

import by.viraz84.myrentcar.dao.mapping.UsersMapper;
import by.viraz84.myrentcar.exception.EntityNotFoundException;
import by.viraz84.myrentcar.exception.EntitySaveException;
import by.viraz84.myrentcar.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UsersDao extends AbstractDao<Users> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersDao.class);

    private static UsersDao instance;

    private static final String INSERT_STATEMENT_USERS = "INSERT INTO users " +
            "(login, pass, user_role, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STATEMENT_USERS = "UPDATE users SET " +
            "login = ?, pass = ?, user_role = ? , first_name = ?, last_name = ?  WHERE id = ?";

    private UsersDao() {
        super(new UsersMapper(), "users");
        instance = this;
    }
    public static UsersDao getInstance() {
        if (instance == null) {
            UsersDao.instance = new UsersDao();
        }
        return instance;
    }

    public Users save (Users user) {
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            if (user.getId() == null){
                ps = connection.prepareStatement(INSERT_STATEMENT_USERS, Statement.RETURN_GENERATED_KEYS);
            }   else {
                ps = connection.prepareStatement(UPDATE_STATEMENT_USERS, Statement.RETURN_GENERATED_KEYS);
            }
            setValue(user, ps);
            if(user.getId() != null) {
                ps.setInt(6, user.getId());
            }
            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }


        } catch (SQLException e) {
            LOGGER.error("Something went wrong during users retrieval", e);
            throw new EntitySaveException(e);
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new EntitySaveException(e);
                }
            }
        }
        return user;
    }

    private void setValue(Users users, PreparedStatement ps) throws SQLException {
        ps.setString(1, users.getLogin());
        ps.setString(2, users.getPass());
        ps.setString(3, users.getUserRole().toString());
        ps.setString(4, users.getFirstName());
        ps.setString(5, users.getLastName());

    }

    public Users findByLogin(String login) {
        String SELECT_BY_LOGIN = String.format(SELECT_STATEMENT, " users ").concat("WHERE login = ").concat("'")
                .concat(login).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_LOGIN);

            if (resultSet.next()) {

                Users entity = getRm().toObject(resultSet);
                return entity;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user retrieval by login=" + login, e);
            throw new EntityNotFoundException(e);
        }
    }



}
