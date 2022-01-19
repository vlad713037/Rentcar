package by.viraz84.myrentcar.model;

import by.viraz84.myrentcar.model.enam.OrdersStatus;
import java.sql.Date;
import java.util.Objects;


public class Orders {
    private Integer id;
    private Integer carId;
    private Integer usersId;
    private OrdersStatus ordersStatus;
    private Date rentStartDate;
    private Date rentEndDate;

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

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id && carId == orders.carId && usersId == orders.usersId &&
                ordersStatus == orders.ordersStatus && Objects.equals(rentStartDate, orders.rentStartDate) &&
                Objects.equals(rentEndDate, orders.rentEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, usersId, ordersStatus, rentStartDate, rentEndDate);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", carId=" + carId +
                ", usersId=" + usersId +
                ", ordersStatus=" + ordersStatus +
                ", rentStartDate=" + rentStartDate +
                ", rentEndDate=" + rentEndDate +
                '}';
    }
}
