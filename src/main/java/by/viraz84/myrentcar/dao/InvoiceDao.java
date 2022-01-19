package by.viraz84.myrentcar.dao;

import by.viraz84.myrentcar.dao.jdbc.ConnectionPoolProvider;
import by.viraz84.myrentcar.dao.mapping.InvoiceMapper;

import by.viraz84.myrentcar.exception.EntitySaveException;
import by.viraz84.myrentcar.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceDao extends AbstractDao<Invoice>{
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDao.class);
    private static InvoiceDao instance;

    private static final String INSERT_STATEMENT_INVOICE = "INSERT INTO invoice " +
            "(orders_id, total_price, status_invoice) VALUES (?, ?, ?)";
    private static final String UPDATE_STATEMENT_INVOICE = "UPDATE invoice " +
            "SET orders_id = ?, total_price = ?, status_invoice = ? WHERE id = ?";

    private InvoiceDao() {
        super(new InvoiceMapper(), "invoice");
        instance = this;
    }

    public static InvoiceDao getInstance(){
        if (instance == null) {
            InvoiceDao.instance = new InvoiceDao();
        }
        return instance;
    }

    public Invoice save(Invoice invoice){
        PreparedStatement ps = null;
        try (Connection connection = ConnectionPoolProvider.getConnection()){
            if (invoice.getId() == null){
                ps = connection.prepareStatement(INSERT_STATEMENT_INVOICE);
            } else {
                ps = connection.prepareStatement(UPDATE_STATEMENT_INVOICE);
            }
            setValues(invoice, ps);

            if (invoice.getId() != null){
                ps.setInt(4,invoice.getId());
            }
            if (ps.executeUpdate() != 1) {
                throw new EntitySaveException("Something went wrong");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during invoice retrieval", e);
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
        return invoice;
    }

    private void setValues(Invoice invoice, PreparedStatement ps) throws SQLException {
        ps.setInt(1, invoice.getOrdersId());
        ps.setFloat(2, invoice.getTotalPrice());
        ps.setString(3, invoice.getInvoiceStatus().toString());
    }


}
