package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.CarMapper;

import by.viraz84.myrentcar.exception.EntitySaveException;
import by.viraz84.myrentcar.model.Car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;


public class CarDao extends AbstractDao<Car>{
    private static final Logger LOGGER = LoggerFactory.getLogger(CarDao.class);

    private static CarDao instance;

    private static final String INSERT_STATEMENT_CAR = "INSERT INTO car " +
            "(brand_name, model_name, price_onday, reg_number, status_car) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STATEMENT_CAR= "UPDATE car " +
            "SET brand_name = ?, model_name = ?, price_onday = ? , reg_number = ?, status_car = ? WHERE id = ?";

    private CarDao() {
        super(new CarMapper(), "car");
        instance = this;
    }
    public static CarDao getInstance(){
        if (instance == null){
            CarDao.instance = new CarDao();
        }
        return instance;
    }

    public Car save(Car car){
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            if (car.getId() == null){
                ps = connection.prepareStatement(INSERT_STATEMENT_CAR, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT_CAR, Statement.RETURN_GENERATED_KEYS);
            }
            setValues(car, ps);
            if (car.getId() != null){
                ps.setInt(6, car.getId());
            }
            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                car.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during cars retrieval", e);
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
        return car;
    }

    private void setValues(Car car, PreparedStatement ps) throws SQLException {
        ps.setString(1, car.getBrandName());
        ps.setString(2, car.getModelName());
        ps.setFloat(3, car.getPriceOnDay());
        ps.setString(4, car.getRegNumber());
        ps.setString(5, car.getCarStatus().toString());
    }




}
