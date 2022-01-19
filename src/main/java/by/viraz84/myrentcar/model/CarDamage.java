package by.viraz84.myrentcar.model;

import by.viraz84.myrentcar.model.enam.DamageStatus;

import java.util.Objects;

public class CarDamage {
    private Integer id;
    private Integer ordersId;
    private String description;
    private Float repairCost;
    private DamageStatus damageStatus;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Float repairCost) {
        this.repairCost = repairCost;
    }

    public DamageStatus getDamageStatus() {
        return damageStatus;
    }

    public void setDamageStatus(DamageStatus damageStatus) {
        this.damageStatus = damageStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDamage carDamage = (CarDamage) o;
        return id == carDamage.id && ordersId == carDamage.ordersId &&
                Objects.equals(description, carDamage.description) &&
                Objects.equals(repairCost, carDamage.repairCost) && damageStatus == carDamage.damageStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordersId, description, repairCost, damageStatus);
    }

    @Override
    public String toString() {
        return "CarDamage{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", description='" + description + '\'' +
                ", repairCost=" + repairCost +
                ", damageStatus=" + damageStatus +
                '}';
    }
}
