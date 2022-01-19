package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.Inner;
import by.viraz84.myrentcar.exception.EntityRetrieveException;
import by.viraz84.myrentcar.model.CarDamageFull;
import by.viraz84.myrentcar.model.enam.DamageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDamageFullDao implements Inner<CarDamageFull> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDamageFullDao.class);

    private static CarDamageFullDao instance;

    private static final String GET_FUL_DAMAGE = "SELECT car.brand_name, car.model_name, car.reg_number, " +
            "car_damage.id AS idDem, car_damage.orders_id, car_damage.description,\n" +
            "car_damage.repair_cost, car_damage.status_damage from car JOIN orders ON car.id = orders.car_id " +
            "JOIN car_damage ON \n" +
            "car_damage.orders_id = orders.id AND car_damage.status_damage ='NOT_FIXED'";

    public static CarDamageFullDao getInstance(){
        if(null == instance){
            CarDamageFullDao.instance = new CarDamageFullDao();
        }
        return  instance;
    }


    @Override
    public List<CarDamageFull> findAllFullNotClose() {
        CarDamageFull carDamageFull = null;
        List<CarDamageFull> allCarDamageFull = new ArrayList<>();
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(GET_FUL_DAMAGE);
             ResultSet resultSet = insertStatement.executeQuery()){
            while (resultSet.next()){
                carDamageFull = new CarDamageFull();
                setValue(carDamageFull, resultSet);
                allCarDamageFull.add(carDamageFull);
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during CarDamageFull retrieval", e);
            throw new EntityRetrieveException(e);
        }

        return allCarDamageFull;
    }

    private void setValue(CarDamageFull carDamageFull, ResultSet resultSet) throws SQLException {
        carDamageFull.setBrandName(resultSet.getString("brand_name"));
        carDamageFull.setModelName(resultSet.getString("model_name"));
        carDamageFull.setRegNumber(resultSet.getString("reg_number"));
        carDamageFull.setId(resultSet.getInt("idDem"));
        carDamageFull.setOrdersId(resultSet.getInt("orders_id"));
        carDamageFull.setDescription(resultSet.getString("description"));
        carDamageFull.setRepairCost(resultSet.getFloat("repair_cost"));
        carDamageFull.setDamageStatus(DamageStatus.valueOf(resultSet.getString("status_damage")));
    }
}
