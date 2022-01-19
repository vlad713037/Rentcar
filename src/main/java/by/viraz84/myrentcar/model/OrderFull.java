package by.viraz84.myrentcar.model;

import by.viraz84.myrentcar.model.enam.OrdersStatus;

import java.sql.Date;
import java.util.Objects;

public class OrderFull {
    private Integer id;
    private Integer carId;
    private OrdersStatus ordersStatus;
    private Date rentStartDate;
    private Date rentEndDate;
    private String firstName;
    private String lastName;
    private String brandName;
    private String modelName;
    private Float priceOnDay;
    private String regNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public OrdersStatus getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(OrdersStatus ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Float getPriceOnDay() {
        return priceOnDay;
    }

    public void setPriceOnDay(Float priceOnDay) {
        this.priceOnDay = priceOnDay;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFull orderFull = (OrderFull) o;
        return Objects.equals(id, orderFull.id) && Objects.equals(carId, orderFull.carId) &&
                ordersStatus == orderFull.ordersStatus && Objects.equals(rentStartDate, orderFull.rentStartDate) &&
                Objects.equals(rentEndDate, orderFull.rentEndDate) && Objects.equals(firstName, orderFull.firstName) &&
                Objects.equals(lastName, orderFull.lastName) && Objects.equals(brandName, orderFull.brandName) &&
                Objects.equals(modelName, orderFull.modelName) && Objects.equals(priceOnDay, orderFull.priceOnDay) &&
                Objects.equals(regNumber, orderFull.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, ordersStatus, rentStartDate, rentEndDate, firstName, lastName, brandName,
                modelName, priceOnDay, regNumber);
    }

    @Override
    public String toString() {
        return "OrderFull{" +
                "id=" + id +
                ", car_id=" + carId +
                ", ordersStatus=" + ordersStatus +
                ", rentStartDate=" + rentStartDate +
                ", rentEndDate=" + rentEndDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", priceOnDay=" + priceOnDay +
                ", regNumber='" + regNumber + '\'' +
                '}';
    }
}
