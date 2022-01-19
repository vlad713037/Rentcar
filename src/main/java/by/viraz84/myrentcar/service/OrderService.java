package by.viraz84.myrentcar.service;

import by.viraz84.myrentcar.dao.OrderFullDao;
import by.viraz84.myrentcar.dao.OrdersDao;
import by.viraz84.myrentcar.model.OrderFull;
import by.viraz84.myrentcar.model.Orders;
import by.viraz84.myrentcar.model.enam.OrdersStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private static OrderService instance;

    private OrderService() {

        instance = this;
    }

    public static OrderService getInstance() {
        if (instance == null) {
            OrderService.instance = new OrderService();
        }
        return instance;
    }


    public boolean ordersThisUserBool(int usID) {
        boolean result = false;
        List<Orders> all = OrdersDao.getInstance().findAll();
        List<Orders> collect = all.stream().filter(ord -> !(ord.getOrdersStatus().equals(OrdersStatus.CLOSE)) &&
                ord.getUsersId() == usID).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            return true;
        }
        return result;
    }

    public Orders ordersThisUser(int usID) {
        List<Orders> all = OrdersDao.getInstance().findAll();
        return all.stream().filter(ord -> !(ord.getOrdersStatus().equals(OrdersStatus.CLOSE)) &&
                ord.getUsersId() == usID).collect(Collectors.toList()).stream().findFirst().get();
    }

    public List<OrderFull> allOrdersOpen() {
        List<OrderFull> all = OrderFullDao.getInstance().findAllFullNotClose();
        return all.stream().filter(ord -> (ord.getOrdersStatus().equals(OrdersStatus.OPEN)))
                .collect(Collectors.toList());
    }

    public List<OrderFull> allOrdersWfp() {
        List<OrderFull> all = OrderFullDao.getInstance().findAllFullNotClose();
        return all.stream().filter(ord -> (ord.getOrdersStatus().equals(OrdersStatus.WAITING_FOR_PAYMENT)))
                .collect(Collectors.toList());
    }

}
