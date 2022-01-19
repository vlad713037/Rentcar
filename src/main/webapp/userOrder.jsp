<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.viraz84.myrentcar.model.enam.OrdersStatus"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="part/header.jsp"%>

<br>
  <c:if test="${sessionScope.user ne null && OrdersStatus.OPEN eq sessionScope.order.ordersStatus}">
      <h3> Please wait. Your application will be reviewed by our company. </h3> <br>
   <div class="container">
       <table class="table table-bordered" >
        <tr>
          <th>brandName</th>
          <th>modelName</th>
          <th>regNumber</th>
          <th>priceOnDay</th>
          <th>rentStartDate</th>
          <th>rentEndDate</th>
          <th>totalPrice</th>
        </tr>
        <tr>
          <td><c:out value="${carById.brandName}"></c:out></td>
          <td><c:out value="${carById.modelName}"></c:out></td>
          <td><c:out value="${carById.regNumber}"></c:out></td>
          <td><c:out value="${carById.priceOnDay}"></c:out></td>
          <td><c:out value="${order.rentStartDate}"></c:out></td>
          <td><c:out value="${order.rentEndDate}"></c:out></td>
          <td><c:out value="${invoice.totalPrice}"></c:out></td>
        </tr>
        </table>
       </div>
 </c:if>

<c:if test="${sessionScope.user ne null && not_approv ne null}">
        <div class="alert alert-danger" role="alert">
          Sorry, your application number ${order.id} has been rejected !!!
        </div>

</c:if>
<c:if test="${sessionScope.user ne null && approv ne null}">
        <div class="alert alert-success" role="alert">
          Congratulations, your car application has been approved. You can get a car!!!
        </div>
        <form class="main_form" action="/rentcar/mainUser"  method="post">
                <table class="table table-bordered">
                        <tr>
                            <th>brandName</th>
                            <th>modelName</th>
                            <th>priceOnDay</th>
                            <th>regNumber</th>
                        </tr>
                        <tbody>
                        <tr>
                            <td><c:out value="${carById.brandName}"></c:out></td>
                            <td><c:out value="${carById.modelName}"></c:out></td>
                            <td><c:out value="${carById.priceOnDay}"></c:out></td>
                            <td><c:out value="${carById.regNumber}"></c:out></td>
                        </tr>
                        </tbody>

                </table>
                <h4>Car rental period from <c:out value="${order.rentStartDate}"></c:out> to
                    <c:out value="${order.rentEndDate}"></c:out> </h4>
                <h4>Car rental price: <c:out value="${invoice.totalPrice}"></c:out> $ </h4>

                <div class="text-center">
                    <input class="btn btn-warning btn-padding-size" type="submit" value="Take a car and go...">
                </div>
            </form>

</c:if>
<c:if test="${sessionScope.user ne null && OrdersStatus.WAITING_FOR_PAYMENT eq sessionScope.order.ordersStatus}">
    <div class="alert alert-info" role="alert">
            <h3>Please ride, then bring the car back!</h3>
    </div>
</c:if>

</body>
</html>
