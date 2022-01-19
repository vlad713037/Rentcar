package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.exception.DateNotParseException;
import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrdersDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);

    }

    @Test
    public void testOrdersDao(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateStart = null;
        java.util.Date dateEnd = null;
        try {
            dateStart = sdf.parse("2022-01-25");
            dateEnd = sdf.parse("2022-01-26");
        } catch (ParseException e) {
            throw new DateNotParseException(e);
        }
        java.sql.Date date1 = new java.sql.Date(dateStart.getTime());
        java.sql.Date date2 = new java.sql.Date(dateEnd.getTime());


        OrdersDao ordersDao = OrdersDao.getInstance();
        Orders o = new Orders();
        o.setCarId(1);
        o.setUsersId(1);
        o.setOrdersStatus(OrdersStatus.OPEN);
        o.setRentStartDate(date1);
        o.setRentEndDate(date2);

        ordersDao.save(o);

        Orders byId = ordersDao.getById(o.getId());
        Assert.assertEquals(byId,o);

        o.setCarId(3);
        ordersDao.save(o);

        int y = ordersDao.getById(o.getId()).getCarId();

        Assert.assertEquals(3,y);

        List<Orders> ordersList = ordersDao.findAllSorded("id ", 10, 0);
        Assert.assertEquals(o,ordersList.get(2));
        Assert.assertEquals(3,ordersList.size());

        ordersDao.deleteById(o.getId());
        Assert.assertNull(ordersDao.getById(o.getId()));
    }

}
