package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.PassportDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.Passport;
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

public class PassportDaoTest {

    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);

    }

    @Test
    public void testPassportDao(){
        PassportDao passportDao = PassportDao.getInstance();
        Passport p = new Passport();
        p.setPassNumber("KH12345678");
        p.setIssuerRovd("OktROVD");
        p.setUsersId(1);

        passportDao.save(p);

        Passport byId = passportDao.getById(p.getId());
        Assert.assertEquals(byId,p);

        p.setIssuerRovd("Grodnenskij ROVD");
        passportDao.save(p);

        Passport p2 = passportDao.getById(p.getId());
        Assert.assertEquals("Grodnenskij ROVD",p2.getIssuerRovd());

        List<Passport> passportList = passportDao.findAllSorded("id", 10, 0);
        Assert.assertEquals(p,passportList.get(5));
        Assert.assertEquals(6,passportList.size());

        passportDao.deleteById(p.getId());
        Assert.assertNull(passportDao.getById(p.getId()));
    }
}
