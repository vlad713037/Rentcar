package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.CarDamageDao;
import by.viraz84.myrentcar.dao.CarDamageFullDao;
import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.CarDamage;
import by.viraz84.myrentcar.model.CarDamageFull;
import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.enam.CarStatus;
import by.viraz84.myrentcar.model.enam.DamageStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/damageList", name = "damageList")
public class DamageListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarDamageFull> allDamage = CarDamageFullDao.getInstance().findAllFullNotClose();
        if (!allDamage.isEmpty()){
            req.setAttribute("allDamage", allDamage);
            req.getRequestDispatcher("/damageList.jsp").forward(req, resp);
        } else {
            req.setAttribute("not_dem", true);
            req.getRequestDispatcher("/admin").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ordIdDem = Integer.parseInt(req.getParameter("ordIdDem"));
        int damageCar = Integer.parseInt(req.getParameter("damageCar"));
        int demId = Integer.parseInt(req.getParameter("demId"));

        Orders byIdOrd = OrdersDao.getInstance().getById(ordIdDem);
        Integer carId = byIdOrd.getCarId();

        CarDamage byIdCarDem = CarDamageDao.getInstance().getById(demId);

        if (damageCar == 1){
            Car fixCar = CarDao.getInstance().getById(carId);
            fixCar.setCarStatus(CarStatus.FREE);
            CarDao.getInstance().save(fixCar);

            byIdCarDem.setDamageStatus(DamageStatus.FIXED);
            CarDamageDao.getInstance().save(byIdCarDem);

            req.setAttribute("fix_car",carId);

        } else if (damageCar == 2){
            byIdCarDem.setDamageStatus(DamageStatus.CRASH);
            CarDamageDao.getInstance().save(byIdCarDem);

            CarDao.getInstance().deleteById(carId);
            req.setAttribute("del_car",carId);
        }

        List<CarDamageFull> allDamage = CarDamageFullDao.getInstance().findAllFullNotClose();
        if (!allDamage.isEmpty()){
            req.setAttribute("allDamage", allDamage);
        } else {
            req.setAttribute("not_dem", true);
        }
        req.getRequestDispatcher("/damageList.jsp").forward(req, resp);
    }
}
