package by.viraz84.mytest;

import by.viraz84.myrentcar.dao.InvoiceDao;
import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.model.Invoice;
import by.viraz84.myrentcar.model.enam.InvoiceStatus;
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

public class InvoiceDaoTest {
    @Before
    public void init() throws SQLException, IOException {
        Statement statement = ConnectionPoolProvider.getConnection().createStatement();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("db_init.sql");

        String string = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
        statement.executeUpdate(string);
    }

    @Test
    public void testInvoiceDao(){
        InvoiceDao invoiceDao = InvoiceDao.getInstance();
        Invoice p = new Invoice();
        p.setOrdersId(1);
        p.setTotalPrice(50f);
        p.setInvoiceStatus(InvoiceStatus.OPEN);

        invoiceDao.save(p);

        Invoice byId = invoiceDao.getById(p.getId());
        Assert.assertEquals(byId,p);

        p.setInvoiceStatus(InvoiceStatus.CLOSE);
        invoiceDao.save(p);

        Invoice p2 = invoiceDao.getById(p.getId());
        Assert.assertEquals(InvoiceStatus.CLOSE,p2.getInvoiceStatus());

        List<Invoice> invoiceList = invoiceDao.findAllSorded("id", 5, 0);
        Assert.assertEquals(p,invoiceList.get(0));
        Assert.assertEquals(1,invoiceList.size());

        invoiceDao.deleteById(p.getId());
        Assert.assertNull(invoiceDao.getById(p.getId()));

    }

}
