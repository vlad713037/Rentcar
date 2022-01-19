package by.viraz84.myrentcar.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/logout", name = "logout")
public class LogoutUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user", null);

        req.getSession().setAttribute("order", null);
        req.getSession().setAttribute("invoice", null);
        req.getSession().setAttribute("carById", null);
        req.getSession().setAttribute("userRole", null);


        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
