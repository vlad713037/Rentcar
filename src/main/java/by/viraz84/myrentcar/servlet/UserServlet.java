package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.enam.CarStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/cabinet", name = "cabinet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int car1 = Integer.parseInt(req.getParameter("car"));
        Car carById = CarDao.getInstance().getById(car1);
        carById.setCarStatus(CarStatus.TAKEN);
        CarDao.getInstance().save(carById);
        req.getSession().setAttribute("carById",carById);

        req.getRequestDispatcher("passport.jsp").forward(req, resp);

    }
}




