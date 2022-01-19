package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/carList", name = "carList")
public class CarListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> allCar = CarDao.getInstance().findAllSorded("brand_name",100,0);
        if (!allCar.isEmpty()){
            req.setAttribute("allCar", allCar);
            req.getRequestDispatcher("/carList.jsp").forward(req, resp);

        } else {
            req.setAttribute("not_car", true);
            req.getRequestDispatcher("/admin").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int carId = Integer.parseInt(req.getParameter("carId"));
        CarDao.getInstance().deleteById(carId);
        req.setAttribute("del_car",carId);

        List<Car> allCar = CarDao.getInstance().findAllSorded("brand_name",100,0);
        if (!allCar.isEmpty()){
            req.setAttribute("allCar", allCar);

        } else {
            req.setAttribute("not_car", true);
        }
        req.getRequestDispatcher("/carList.jsp").forward(req, resp);
    }
}
