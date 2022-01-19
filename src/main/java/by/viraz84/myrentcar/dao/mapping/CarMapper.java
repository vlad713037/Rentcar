package by.viraz84.myrentcar.dao.mapping;

import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.enam.CarStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car>{
    @Override
    public Car toObject(ResultSet rs) throws SQLException {
        Car p = new Car();

        p.setId(rs.getInt("id"));
        p.setBrandName(rs.getString("brand_name"));
        p.setModelName(rs.getString("model_name"));
        p.setPriceOnDay(rs.getFloat("price_onday"));
        p.setRegNumber(rs.getString("reg_number"));
        p.setCarStatus(CarStatus.valueOf(rs.getString("status_car")));



        return p;

    }
}
