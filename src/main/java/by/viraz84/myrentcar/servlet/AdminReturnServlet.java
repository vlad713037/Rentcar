package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.CarDamageDao;
import by.viraz84.myrentcar.dao.CarDao;
import by.viraz84.myrentcar.dao.InvoiceDao;
import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.model.Car;
import by.viraz84.myrentcar.model.CarDamage;
import by.viraz84.myrentcar.model.Invoice;
import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.enam.CarStatus;
import by.viraz84.myrentcar.model.enam.DamageStatus;
import by.viraz84.myrentcar.model.enam.InvoiceStatus;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import by.viraz84.myrentcar.service.InvoiceService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/adminReturn", name = "adminReturn")
public class AdminReturnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int damage = Integer.parseInt(req.getParameter("damage"));
        int ordId = Integer.parseInt(req.getParameter("ordId"));
        int carId = Integer.parseInt(req.getParameter("carId"));
        Orders order = OrdersDao.getInstance().getById(ordId);
        order.setOrdersStatus(OrdersStatus.CLOSE);
        OrdersDao.getInstance().save(order);

        Invoice invoice = InvoiceService.getInstance().invoiceThisOrder(ordId);
        invoice.setInvoiceStatus(InvoiceStatus.CLOSE);
        InvoiceDao.getInstance().save(invoice);

        if (damage == 1) {
            req.setAttribute("not_damage", true);
            Car carById = CarDao.getInstance().getById(carId);
            carById.setCarStatus(CarStatus.FREE);
            CarDao.getInstance().save(carById);
            req.getRequestDispatcher("/admin").forward(req, resp);
        } else {
            req.setAttribute("damage", true);
            Car carById = CarDao.getInstance().getById(carId);
            carById.setCarStatus(CarStatus.BROKEN);
            CarDao.getInstance().save(carById);
            req.setAttribute("carDemage",carById);
            req.getSession().setAttribute("ordId",ordId);
            req.getSession().setAttribute("carId",carId);
            req.getRequestDispatcher("/damage.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        Float repairCost = Float.parseFloat(req.getParameter("repair_cost"));

        int carId = (Integer) req.getSession().getAttribute("carId");
        int ordId = (Integer) req.getSession().getAttribute("ordId");

        CarDamage carDamage = new CarDamage();
        carDamage.setDescription(description);
        carDamage.setRepairCost(repairCost);
        carDamage.setOrdersId(ordId);
        carDamage.setDamageStatus(DamageStatus.NOT_FIXED);
        CarDamageDao.getInstance().save(carDamage);
        req.setAttribute("demCar",CarDao.getInstance().getById(carId));

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);


    }
}
