package by.viraz84.myrentcar.dao.mapping;

import by.viraz84.myrentcar.model.CarDamage;
import by.viraz84.myrentcar.model.enam.DamageStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDamageMapper implements RowMapper<CarDamage>{
    @Override
    public CarDamage toObject(ResultSet rs) throws SQLException {
        CarDamage p = new CarDamage();

        p.setId(rs.getInt("id"));
        p.setOrdersId(rs.getInt("orders_id"));
        p.setDescription(rs.getString("description"));
        p.setRepairCost(rs.getFloat("repair_cost"));
        p.setDamageStatus(DamageStatus.valueOf(rs.getString("status_damage")));


        return p;
    }
}
