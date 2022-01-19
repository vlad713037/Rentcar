package by.viraz84.myrentcar.dao.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T toObject(ResultSet rs) throws SQLException;
}
