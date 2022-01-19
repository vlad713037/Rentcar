package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.CarDamageDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.CarDamage;
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

public class CarDamageDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);

    }

    @Test
    public void testCarDamageDao(){
        CarDamageDao carDamageDao = CarDamageDao.getInstance();
        CarDamage p = new CarDamage();
        p.setOrdersId(1);
        p.setDescription("scratched left car fender");
        p.setRepairCost(1000f);
        p.setDamageStatus(DamageStatus.NOT_FIXED);

        carDamageDao.save(p);

        CarDamage byId = carDamageDao.getById(p.getId());
        Assert.assertEquals(byId,p);

        p.setDescription("scratched right car fender");
        p.setDamageStatus(DamageStatus.FIXED);
        carDamageDao.save(p);

        CarDamage p2 = carDamageDao.getById(p.getId());
        Assert.assertEquals("scratched right car fender",p2.getDescription());
        Assert.assertEquals(DamageStatus.FIXED,p2.getDamageStatus());

        List<CarDamage> carDamageList = carDamageDao.findAllSorded("id", 5, 0);
        Assert.assertEquals(p,carDamageList.get(1));
        Assert.assertEquals(2,carDamageList.size());

        carDamageDao.deleteById(p.getId());
        Assert.assertNull(carDamageDao.getById(p.getId()));

    }

}
