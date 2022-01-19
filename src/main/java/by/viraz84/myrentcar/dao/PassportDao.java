package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.PassportMapper;

import by.viraz84.myrentcar.exception.EntitySaveException;
import by.viraz84.myrentcar.model.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PassportDao extends AbstractDao<Passport>{
    private static final Logger LOGGER = LoggerFactory.getLogger(PassportDao.class);

    private static PassportDao instance;

    private static final String INSERT_STATEMENT_PASSPORT = "INSERT INTO passport " +
            "(pass_number, issuer_rovd, users_id) VALUES (?, ?, ?)";
    private static final String UPDATE_STATEMENT_PASSPORT = "UPDATE passport " +
            "SET pass_number = ?, issuer_rovd = ?, users_id = ?  WHERE id = ?";

    private PassportDao() {
        super(new PassportMapper(), "passport");
        instance = this;
    }

    public static PassportDao getInstance() {
        if (instance == null) {
            PassportDao.instance = new PassportDao();
        }
        return instance;
    }

    public Passport save(Passport passport){
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            if (passport.getId() == null){
                ps = connection.prepareStatement(INSERT_STATEMENT_PASSPORT, Statement.RETURN_GENERATED_KEYS);
             } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT_PASSPORT, Statement.RETURN_GENERATED_KEYS);
            }

            setValues(passport, ps);
            if (passport.getId() != null){
                ps.setInt(4, passport.getId());
            }

            if (ps.executeUpdate() != 1 ){
                throw new EntitySaveException("Something went wrong");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()){
                passport.setId(generatedKeys.getInt(1));
            }


        } catch (SQLException e) {
            LOGGER.error("Something went wrong during passports retrieval", e);
            throw new EntitySaveException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new EntitySaveException(e);
                }
            }
        }
        return passport;
    }

    private void setValues(Passport passport, PreparedStatement ps) throws SQLException {
        ps.setString(1, passport.getPassNumber());
        ps.setString(2, passport.getIssuerRovd());
        ps.setInt(3, passport.getUsersId());
    }

}
