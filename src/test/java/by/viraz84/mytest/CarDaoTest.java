package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.enam.CarStatus;
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

public class CarDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);

    }

    @Test
    public void testCarDao(){
        CarDao carDao = CarDao.getInstance();
        Car p = new Car();
        p.setBrandName("Volvo");
        p.setModelName("XC60");
        p.setPriceOnDay(90F);
        p.setRegNumber("0250AA4");
        p.setCarStatus(CarStatus.FREE);

        carDao.save(p);

        Car byId = carDao.getById(p.getId());
        Assert.assertEquals(byId,p);

        p.setRegNumber("0255AA7");
        p.setCarStatus(CarStatus.TAKEN);
        carDao.save(p);

        Car p2 = carDao.getById(p.getId());
        Assert.assertEquals("0255AA7",p2.getRegNumber());
        Assert.assertEquals(CarStatus.TAKEN,p2.getCarStatus());

        List<Car> carList = carDao.findAllSorded("brand_name", 5, 0);
        Assert.assertEquals(p,carList.get(4));
        Assert.assertEquals(5,carList.size());

        carDao.deleteById(p.getId());
        Assert.assertNull(carDao.getById(p.getId()));

    }
}
