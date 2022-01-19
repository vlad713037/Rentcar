package by.viraz84.myrentcar.service;

import by.viraz84.myrentcar.dao.InvoiceDao;
import by.viraz84.myrentcar.model.Invoice;
import by.viraz84.myrentcar.model.enam.InvoiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService {
    public static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    private static InvoiceService instance;

    private InvoiceService() {

        instance = this;
    }

    public static InvoiceService getInstance() {
        if (instance == null) {
            InvoiceService.instance = new InvoiceService();
        }
        return instance;
    }

    public Invoice invoiceThisOrder(int ordId){
        List<Invoice> all = InvoiceDao.getInstance().findAll();
        return all.stream().filter(inv -> (inv.getInvoiceStatus().equals(InvoiceStatus.OPEN)) &&
                inv.getOrdersId() == ordId).collect(Collectors.toList()).stream().findFirst().get();
    }





}
