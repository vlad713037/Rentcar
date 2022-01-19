package by.viraz84.myrentcar.service;

import by.viraz84.myrentcar.dao.CarDamageDao;
import by.viraz84.myrentcar.model.CarDamage;
import by.viraz84.myrentcar.model.enam.DamageStatus;

import java.util.List;
import java.util.stream.Collectors;

public class DamageService {
    private static DamageService instance;

    private DamageService() {

        instance = this;
    }

    public static DamageService getInstance() {
        if (instance == null) {
            DamageService.instance = new DamageService();
        }
        return instance;
    }

    public List<CarDamage> DamageListNotFixed(){
        List<CarDamage> all = CarDamageDao.getInstance().findAll();
        return all.stream().filter(dem -> (dem.getDamageStatus().equals(DamageStatus.NOT_FIXED)))
                .collect(Collectors.toList());
    }
}

