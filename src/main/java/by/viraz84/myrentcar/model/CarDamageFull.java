package by.viraz84.myrentcar.model;

import by.viraz84.myrentcar.model.enam.DamageStatus;

public class CarDamageFull {
    private String brandName;
    private String modelName;
    private String regNumber;
    private Integer id;
    private Integer ordersId;
    private String description;
    private Float repairCost;
    private DamageStatus damageStatus;

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


    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

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
    public String toString() {
        return "CarDamageFull{" +
                "brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", id=" + id +
                ", ordersId=" + ordersId +
                ", description='" + description + '\'' +
                ", repairCost=" + repairCost +
                ", damageStatus=" + damageStatus +
                '}';
    }
}
