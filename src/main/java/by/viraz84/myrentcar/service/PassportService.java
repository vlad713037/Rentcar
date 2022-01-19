package by.viraz84.myrentcar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;


public class PassportService {
    public static final Logger LOGGER = LoggerFactory.getLogger(PassportService.class);

    private static PassportService instance;

    private PassportService() {

        instance = this;
    }

    public static PassportService getInstance() {
        if (instance == null) {
            PassportService.instance = new PassportService();
        }
        return instance;
    }

    public long diffInDays (LocalDate a, LocalDate b) {
        return DAYS.between(a, b)+1;
    }

    public Float totalPrice (Float price, long days){
        return price*days;

    }

}
