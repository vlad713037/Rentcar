package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.OrdersMapper;

import by.viraz84.myrentcar.exception.EntitySaveException;
import by.viraz84.myrentcar.model.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class OrdersDao extends AbstractDao<Orders>{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersDao.class);
    private static OrdersDao instance;

    private static final String INSERT_STATEMENT_ORDERS = "INSERT INTO orders " +
            "(car_id, users_id, status_orders, rent_startdate, rent_enddate) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STATEMENT_ORDERS = "UPDATE orders " +
            "SET car_id = ?, users_id = ?, status_orders = ? , rent_startdate = ?, rent_enddate = ? WHERE id = ?";

    private OrdersDao() {
        super(new OrdersMapper(), "orders");
        instance = this;
    }

    public static OrdersDao getInstance(){
        if(null == instance){
            OrdersDao.instance = new OrdersDao();
        }
        return  instance;
    }

    public Orders save(Orders order){
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            if (order.getId() == null){
                ps = connection.prepareStatement(INSERT_STATEMENT_ORDERS, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT_ORDERS,Statement.RETURN_GENERATED_KEYS);
            }

            setValues(order, ps);

            if (order.getId() != null) {
                ps.setInt(6, order.getId());
            }

            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during orders retrieval", e);
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
        return order;
    }

    private void setValues(Orders order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getCarId());
        ps.setInt(2, order.getUsersId());
        ps.setString(3, order.getOrdersStatus().toString());
        ps.setDate(4,  order.getRentStartDate());
        ps.setDate(5, order.getRentEndDate());
    }


}
