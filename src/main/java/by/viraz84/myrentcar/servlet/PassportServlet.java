package by.viraz84.myrentcar.servlet;

import by.viraz84.myrentcar.dao.InvoiceDao;
import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.dao.PassportDao;
import by.viraz84.myrentcar.exception.DateNotParseException;
import by.viraz84.myrentcar.model.*;
import by.viraz84.myrentcar.model.enam.InvoiceStatus;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import by.viraz84.myrentcar.service.PassportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@WebServlet(value = "/passp", name = "passp")
public class PassportServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users userAttribute  = (Users) req.getSession().getAttribute("user");
        Car carAttribute = (Car) req.getSession().getAttribute("carById");

        String passNum = req.getParameter("passNumber");
        String issRovd = req.getParameter("issuerRovd");


        String parameter=req.getParameter("startRent");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = null;
        try {
            dateStart = sdf.parse(parameter);
        } catch (ParseException e) {
            throw new DateNotParseException(e);
        }
        java.sql.Date date1 = new java.sql.Date(dateStart.getTime());

        String parameter2=req.getParameter("endRent");
        Date dateEnd = null;
        try {
            dateEnd = sdf.parse(parameter2);
        } catch (ParseException e) {
            throw new DateNotParseException(e);
        }
        java.sql.Date date2 = new java.sql.Date(dateEnd.getTime());

        LocalDate localDate1 = date1.toLocalDate();
        LocalDate localDate2 = date2.toLocalDate();
        long days = PassportService.getInstance().diffInDays(localDate1, localDate2);

        Integer id = userAttribute.getId();
        Passport passport = new Passport();
        passport.setUsersId(id);
        passport.setPassNumber(passNum);
        passport.setIssuerRovd(issRovd);
        PassportDao.getInstance().save(passport);

        Orders order = new Orders();
        order.setCarId(carAttribute.getId());
        order.setUsersId(id);
        order.setOrdersStatus(OrdersStatus.OPEN);
        order.setRentStartDate(date1);
        order.setRentEndDate(date2);
        Orders saveOrd = OrdersDao.getInstance().save(order);

        req.getSession().setAttribute("order", saveOrd);

        Invoice invoice = new Invoice();
        invoice.setOrdersId(saveOrd.getId());
        invoice.setTotalPrice(PassportService.getInstance().totalPrice(carAttribute.getPriceOnDay(),days));
        invoice.setInvoiceStatus(InvoiceStatus.OPEN);
        Invoice saveInv = InvoiceDao.getInstance().save(invoice);

        req.getSession().setAttribute("invoice", saveInv);

        req.getRequestDispatcher("userOrder.jsp").forward(req,resp);
    }


}
