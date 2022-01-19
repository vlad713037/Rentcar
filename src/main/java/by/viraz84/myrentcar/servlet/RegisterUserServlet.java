package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.service.CarService;
import by.viraz84.myrentcar.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/register", name = "register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        List<Car> allCarFree = CarService.getInstance().findAllCarFree();
        req.setAttribute("allCarFree", allCarFree);

        req.getSession().setAttribute("user", UserService.getInstance().registerUser(login, password, fname, lname));
        req.getRequestDispatcher("userCabinet.jsp").forward(req,resp);

    }
}
