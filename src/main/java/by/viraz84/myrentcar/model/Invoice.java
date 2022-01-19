package by.viraz84.myrentcar.model;

import by.viraz84.myrentcar.model.enam.InvoiceStatus;

import java.util.Objects;

public class Invoice {
    private Integer id;
    private Integer ordersId;
    private Float totalPrice;
    private InvoiceStatus invoiceStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && ordersId == invoice.ordersId && Objects.equals(totalPrice, invoice.totalPrice) &&
                invoiceStatus == invoice.invoiceStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordersId, totalPrice, invoiceStatus);
    }


}
