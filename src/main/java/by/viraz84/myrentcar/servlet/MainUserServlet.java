package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.Invoice;
import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.Users;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import by.viraz84.myrentcar.service.CarService;
import by.viraz84.myrentcar.service.InvoiceService;
import by.viraz84.myrentcar.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/mainUser", name = "mainUser")
public class MainUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Users user = (Users) req.getSession().getAttribute("user");
        Integer idUs = user.getId();
        boolean boolOrd = OrderService.getInstance().ordersThisUserBool(idUs);
        if (boolOrd && OrderService.getInstance().ordersThisUser(idUs).getOrdersStatus().equals(OrdersStatus.OPEN)) {
            Orders order = OrderService.getInstance().ordersThisUser(idUs);
            req.getSession().setAttribute("order", order);

            Integer carId = order.getCarId();
            Car carById = CarDao.getInstance().getById(carId);
            req.getSession().setAttribute("carById", carById);

            Integer idOrd = order.getId();
            Invoice invoice = InvoiceService.getInstance().invoiceThisOrder(idOrd);
            req.getSession().setAttribute("invoice", invoice);

            req.getRequestDispatcher("userOrder.jsp").forward(req, resp);
        } else if (boolOrd &&
                OrderService.getInstance().ordersThisUser(idUs).getOrdersStatus().equals(OrdersStatus.DENIED)) {
            Orders order = OrderService.getInstance().ordersThisUser(idUs);

            order.setOrdersStatus(OrdersStatus.CLOSE);
            OrdersDao.getInstance().save(order);
            req.getSession().setAttribute("order", order);
            req.setAttribute("not_approv", true);

            req.getRequestDispatcher("userOrder.jsp").forward(req, resp);
        } else if (boolOrd &&
                OrderService.getInstance().ordersThisUser(idUs).getOrdersStatus().equals(OrdersStatus.APPROVED)) {
            Orders order = OrderService.getInstance().ordersThisUser(idUs);
            Integer carId = order.getCarId();
            Integer id = order.getId();

            req.getSession().setAttribute("order", order);
            req.getSession().setAttribute("carById", CarDao.getInstance().getById(carId));
            req.getSession().setAttribute("invoice", InvoiceService.getInstance().invoiceThisOrder(id));
            req.setAttribute("approv", true);

            req.getRequestDispatcher("userOrder.jsp").forward(req, resp);

        }  else if (boolOrd && OrderService.getInstance().ordersThisUser(idUs).getOrdersStatus().
                equals(OrdersStatus.WAITING_FOR_PAYMENT)) {
            Orders order = OrderService.getInstance().ordersThisUser(idUs);
            req.getSession().setAttribute("order", order);
            req.getRequestDispatcher("userOrder.jsp").forward(req, resp);
        }
        else {
            List<Car> allCarFree = CarService.getInstance().findAllCarFree();
            req.setAttribute("allCarFree", allCarFree);
            req.getRequestDispatcher("userCabinet.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        Integer idUs = user.getId();
        Orders order = OrderService.getInstance().ordersThisUser(idUs);
        order.setOrdersStatus(OrdersStatus.WAITING_FOR_PAYMENT);
        OrdersDao.getInstance().save(order);

        req.getSession().setAttribute("order", order);
        req.getRequestDispatcher("userOrder.jsp").forward(req, resp);
    }
}
