package by.viraz84.myrentcar.model;

import by.viraz84.myrentcar.model.enam.CarStatus;
import java.util.Objects;

public class Car {
    private Integer id;
    private String brandName;
    private String modelName;
    private Float priceOnDay;
    private String regNumber;
    private CarStatus carStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(brandName, car.brandName) &&
                Objects.equals(modelName, car.modelName) && Objects.equals(priceOnDay, car.priceOnDay) &&
                Objects.equals(regNumber, car.regNumber) && carStatus == car.carStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, modelName, priceOnDay, regNumber, carStatus);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", priceOnDay=" + priceOnDay +
                ", regNumber='" + regNumber + '\'' +
                ", carStatus=" + carStatus +
                '}';
    }
}
