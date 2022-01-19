package by.viraz84.myrentcar.dao.mapping;

import by.viraz84.myrentcar.model.Users;
import by.viraz84.myrentcar.model.enam.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users>{

    @Override
    public Users toObject(ResultSet rs) throws SQLException {
        Users p = new Users();

        p.setId(rs.getInt("id"));
        p.setLogin(rs.getString("login"));
        p.setPass(rs.getString("pass"));
        p.setUserRole(UserRole.valueOf(rs.getString("user_role")));
        p.setFirstName(rs.getString("first_name"));
        p.setLastName(rs.getString("last_name"));


        return p;
    }


}
