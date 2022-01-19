package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.CarDamageMapper;

import by.viraz84.myrentcar.exception.EntitySaveException;
import by.viraz84.myrentcar.model.CarDamage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class CarDamageDao extends AbstractDao<CarDamage>{
    private static final Logger LOGGER = LoggerFactory.getLogger(CarDamageDao.class);
    private static CarDamageDao instance;

    private static final String INSERT_STATEMENT_CAR_DAMAGE = "INSERT INTO car_damage " +
            "(orders_id, description, repair_cost, status_damage) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_STATEMENT_CAR_DAMAGE = "UPDATE car_damage " +
            "SET orders_id = ?, description = ?, repair_cost = ?, status_damage = ? WHERE id = ?";


    private CarDamageDao() {
        super(new CarDamageMapper(), "car_damage");
        instance = this;
    }

    public static CarDamageDao getInstance(){
        if(null == instance){
            CarDamageDao.instance = new CarDamageDao();
        }
        return instance;
    }

    public CarDamage save(CarDamage carDamage){
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            if (null == carDamage.getId()){
                ps = connection.prepareStatement(INSERT_STATEMENT_CAR_DAMAGE, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT_CAR_DAMAGE, Statement.RETURN_GENERATED_KEYS);
            }
            setValues(carDamage, ps);
            if (carDamage.getId() != null) {
                ps.setInt(5, carDamage.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                carDamage.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during car_damage retrieval", e);
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
        return carDamage;
    }

    private void setValues(CarDamage carDamage, PreparedStatement ps) throws SQLException {
        ps.setInt(1, carDamage.getOrdersId());
        ps.setString(2, carDamage.getDescription());
        ps.setFloat(3, carDamage.getRepairCost());
        ps.setString(4, carDamage.getDamageStatus().toString());
    }

}
