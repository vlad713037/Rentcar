package by.viraz84.myrentcar.dao.mapping;

import by.viraz84.myrentcar.model.Invoice;
import by.viraz84.myrentcar.model.enam.InvoiceStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper implements RowMapper<Invoice>{
    @Override
    public Invoice toObject(ResultSet rs) throws SQLException {
        Invoice p = new Invoice();

        p.setId(rs.getInt("id"));
        p.setOrdersId(rs.getInt("orders_id"));
        p.setTotalPrice(rs.getFloat("total_price"));
        p.setInvoiceStatus(InvoiceStatus.valueOf(rs.getString("status_invoice")));


        return p;
    }
}
