package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.CarDamageFullDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.CarDamageFull;
import by.viraz84.myrentcar.model.enam.DamageStatus;
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

public class CarDamageFullDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);
    }
    @Test
    public void testCarDamageFullDao(){
        CarDamageFullDao carDamageFullDao = CarDamageFullDao.getInstance();
        List<CarDamageFull> allFullNotClose = carDamageFullDao.findAllFullNotClose();
        Assert.assertEquals(1,allFullNotClose.size());
        Assert.assertEquals(DamageStatus.NOT_FIXED,allFullNotClose.get(0).getDamageStatus());
        Assert.assertEquals("scratched right_car fender",allFullNotClose.get(0).getDescription());
        Assert.assertEquals("Ford",allFullNotClose.get(0).getBrandName());
        Assert.assertEquals("0004AA4",allFullNotClose.get(0).getRegNumber());
        Assert.assertEquals("Focus" ,allFullNotClose.get(0).getModelName());

    }


}
