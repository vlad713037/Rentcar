package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.service.CarService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addCar", name = "addCar")
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        Float priceDay = Float.parseFloat(req.getParameter("price_day"));
        String regNumb = req.getParameter("reg_numb");

        req.setAttribute("carCreate", CarService.getInstance().carCreate(brand,model,priceDay,regNumb));

        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
