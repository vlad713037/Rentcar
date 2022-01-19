package by.viraz84.myrentcar.dao.mapping;

import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.enam.OrdersStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersMapper implements RowMapper<Orders>{
    @Override
    public Orders toObject(ResultSet rs) throws SQLException {
        Orders p =new Orders();

        p.setId(rs.getInt("id"));
        p.setCarId(rs.getInt("car_id"));
        p.setUsersId(rs.getInt("users_id"));
        p.setOrdersStatus(OrdersStatus.valueOf(rs.getString("status_orders")));
        p.setRentStartDate(rs.getDate("rent_startdate"));
        p.setRentEndDate(rs.getDate("rent_enddate"));

        return p;
    }
}
