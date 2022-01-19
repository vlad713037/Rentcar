package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.UsersDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.Users;
import by.viraz84.myrentcar.model.enam.UserRole;
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

public class UserDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);

    }


    @Test
    public void testUserDaoInsertGet(){
       UsersDao usersDao = UsersDao.getInstance();
        Users user = new Users();
        user.setLogin("crow@tut");
        user.setPass("pasw");
        user.setFirstName("Nic");
        user.setLastName("Scot");
        user.setUserRole(UserRole.USER);

        usersDao.save(user);

        Users byId = usersDao.getById(user.getId());

        Assert.assertEquals(byId,user);

        user.setFirstName("Adam");
        usersDao.save(user);

        Users user2 = usersDao.getById(user.getId());
        Assert.assertEquals("Adam",user2.getFirstName());

        List<Users> allSorded = usersDao.findAllSorded("first_name", 10, 0);
        Assert.assertEquals(user,allSorded.get(0));
        Assert.assertEquals(6,allSorded.size());

        usersDao.deleteById(user.getId());
        Assert.assertNull(usersDao.getById(user.getId()));
    }

}
