package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.Inner;

import by.viraz84.myrentcar.exception.EntityRetrieveException;
import by.viraz84.myrentcar.model.OrderFull;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderFullDao implements Inner<OrderFull> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFullDao.class);

    private static OrderFullDao instance;

     private static final String GET_FUL_ORDERS = "SELECT orders.id AS idOrd, orders.car_id, orders.status_orders," +
            " orders.rent_startdate, orders.rent_enddate, users.first_name,\n" +
            "users.last_name, car.brand_name, car.model_name, car.price_onday, car.reg_number \n" +
            "FROM orders\n" +
            "JOIN car ON car.id = orders.car_id AND orders.status_orders !='CLOSE'\n" +
            "JOIN users ON users.id=orders.users_id\n" +
            "ORDER BY users.last_name";

    public static OrderFullDao getInstance(){
        if(null == instance){
            OrderFullDao.instance = new OrderFullDao();
        }
        return  instance;
    }


    @Override
    public List<OrderFull> findAllFullNotClose() {
        OrderFull orderFull = null;
        List<OrderFull> allOrderFull = new ArrayList<>();
        try (Connection connection = ConnectionPoolProvider.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(GET_FUL_ORDERS);
             ResultSet resultSet = insertStatement.executeQuery()){
            while (resultSet.next()){
                orderFull = new OrderFull();
                setValues(orderFull, resultSet);
                allOrderFull.add(orderFull);
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during OrderFull retrieval", e);
            throw new EntityRetrieveException(e);
        }

        return allOrderFull;
    }

    private void setValues(OrderFull orderFull, ResultSet resultSet) throws SQLException {
        orderFull.setId(resultSet.getInt("idOrd"));
        orderFull.setCarId(resultSet.getInt("car_id"));
        orderFull.setOrdersStatus(OrdersStatus.valueOf(resultSet.getString("status_orders")));
        orderFull.setRentStartDate(resultSet.getDate("rent_startdate"));
        orderFull.setRentEndDate(resultSet.getDate("rent_enddate"));
        orderFull.setFirstName(resultSet.getString("first_name"));
        orderFull.setLastName(resultSet.getString("last_name"));
        orderFull.setBrandName(resultSet.getString("brand_name"));
        orderFull.setModelName(resultSet.getString("model_name"));
        orderFull.setPriceOnDay(resultSet.getFloat("price_onday"));
        orderFull.setRegNumber(resultSet.getString("reg_number"));
    }
}
