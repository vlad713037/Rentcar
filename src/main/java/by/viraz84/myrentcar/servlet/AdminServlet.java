package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.OrderFull;
import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.enam.CarStatus;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import by.viraz84.myrentcar.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(value = "/admin", name = "admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String role = String.valueOf(req.getSession().getAttribute("userRole"));

        if (role.equals("ADMIN")) {
            List<OrderFull> ordersOpen = OrderService.getInstance().allOrdersOpen();
            List<OrderFull> ordersWfp = OrderService.getInstance().allOrdersWfp();

            if (ordersOpen.size()>0) {
                req.setAttribute("listOpen", ordersOpen);
            }
            if (ordersWfp.size() >0){
                req.setAttribute("listWfp", ordersWfp);
            }
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect("/login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType ("text/html;charset=utf-8");
        int ordId = Integer.parseInt(req.getParameter("ordId"));
        int carId = Integer.parseInt(req.getParameter("carId"));
        int status = Integer.parseInt(req.getParameter("status"));

        if (status == 1){
            Orders byId = OrdersDao.getInstance().getById(ordId);
            byId.setOrdersStatus(OrdersStatus.APPROVED);
            OrdersDao.getInstance().save(byId);

            req.setAttribute("approv", true);

        } else if (status == 2){
            Orders byId = OrdersDao.getInstance().getById(ordId);
            byId.setOrdersStatus(OrdersStatus.DENIED);
            OrdersDao.getInstance().save(byId);

            Car byIdCar = CarDao.getInstance().getById(carId);
            byIdCar.setCarStatus(CarStatus.FREE);
            CarDao.getInstance().save(byIdCar);

            req.setAttribute("not_approv", true);

        }
        List<OrderFull> ordersOpen = OrderService.getInstance().allOrdersOpen();
        List<OrderFull> ordersWfp = OrderService.getInstance().allOrdersWfp();
        if (ordersOpen.size()>0) {
            req.setAttribute("listOpen", ordersOpen);
        }
        if (ordersWfp.size() >0){
            req.setAttribute("listWfp", ordersWfp);
        }

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);

    }
}
