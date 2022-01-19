package by.viraz84.myrentcar.service;

import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.enam.CarStatus;

import java.util.List;
import java.util.stream.Collectors;

public class CarService {
    private static CarService instance;

    private CarService() {

        instance = this;
    }

    public static CarService getInstance() {
        if (instance == null) {
            CarService.instance = new CarService();
        }
        return instance;
    }
    public List<Car> findAllCarFree() {
        List<Car> all = CarDao.getInstance().findAllSorded("brand_name",100,0);
        return all.stream().filter(car -> (car.getCarStatus().equals(CarStatus.FREE)))
                .collect(Collectors.toList());
    }

    public Car carCreate(String brand, String model, Float priceDay, String number){
        Car car = new Car();
        car.setBrandName(brand);
        car.setModelName(model);
        car.setPriceOnDay(priceDay);
        car.setRegNumber(number);
        car.setCarStatus(CarStatus.FREE);

        Car save = CarDao.getInstance().save(car);
        return save;
    }




}
