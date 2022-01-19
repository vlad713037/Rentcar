package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.OrderFullDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.OrderFull;
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
import java.util.List;

public class OrderFullDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);
    }
    @Test
    public void testOrderFullDao(){
        OrderFullDao orderFullDao = OrderFullDao.getInstance();
        List<OrderFull> allFullNotClose = orderFullDao.findAllFullNotClose();
        Assert.assertEquals(2,allFullNotClose.size());
        Assert.assertEquals("Audi",allFullNotClose.get(0).getBrandName());
        Assert.assertEquals("Ford",allFullNotClose.get(1).getBrandName());
        Assert.assertEquals("Petr",allFullNotClose.get(0).getFirstName());
        Assert.assertEquals("Makar",allFullNotClose.get(1).getFirstName());
        Assert.assertEquals(OrdersStatus.OPEN,allFullNotClose.get(0).getOrdersStatus());
        Assert.assertEquals(OrdersStatus.OPEN,allFullNotClose.get(1).getOrdersStatus());
    }

}
