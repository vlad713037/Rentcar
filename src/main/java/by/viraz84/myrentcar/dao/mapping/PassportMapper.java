package by.viraz84.myrentcar.dao.mapping;

import by.viraz84.myrentcar.model.Passport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassportMapper implements RowMapper<Passport>{


    @Override
    public Passport toObject(ResultSet rs) throws SQLException {
        Passport p = new Passport();
        p.setId(rs.getInt("id"));
        p.setPassNumber(rs.getString("pass_number"));
        p.setIssuerRovd(rs.getString("issuer_rovd"));
        p.setUsersId(rs.getInt("users_id"));

        return p;
    }
}
