<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.viraz84.myrentcar.model.enam.OrdersStatus"%>
<%@ page import="by.viraz84.myrentcar.model.enam.UserRole"%>
<%@ page import="by.viraz84.myrentcar.model.enam.CarStatus"%>
<%@include file="part/header.jsp"%>


<c:if test="${listOpen ne null}">
         <table class="table table-bordered">
             <tr>
                <th>brandName</th>
                 <th>modelName</th>
                 <th>priceOnDay</th>
                 <th>regNumber</th>
                 <th>firstName</th>
                 <th>lastName</th>
                 <th>ordersStatus</th>
                 <th>rentStartDate</th>
                 <th>rentEndDate</th>
                 <th> </th>
                 <th> </th>
             </tr>
             <c:forEach items="${listOpen}" var="ord">
                <form action="/rentcar/admin"  method="post">
                 <tr>
                     <input type="hidden" name="ordId" value="${ord.id}" />
                     <input type="hidden" name="carId" value="${ord.carId}" />
                     <td><c:out value="${ord.brandName}"></c:out></td>
                     <td><c:out value="${ord.modelName}"></c:out></td>
                     <td><c:out value="${ord.priceOnDay}"></c:out></td>
                     <td><c:out value="${ord.regNumber}"></c:out></td>
                     <td><c:out value="${ord.firstName}"></c:out></td>
                     <td><c:out value="${ord.lastName}"></c:out></td>
                     <td><c:out value="${ord.ordersStatus}"></c:out></td>
                     <td><c:out value="${ord.rentStartDate}"></c:out></td>
                     <td><c:out value="${ord.rentEndDate}"></c:out></td>
                     <td><select class="form-control" id="records" name="status">
                                                 <option value="1" >APPROVED</option>
                                                 <option value="2" >DENIED</option>
                           </select>
                      </td>
                       <td> <div style="float: left; padding: 5px">
                           <button type="submit" class="btn btn-primary"> Submit </button>  </div></td>
                 </tr>
                   </form>
             </c:forEach>
        </table>
</c:if>

 <c:if test="${approv ne null}">
                   <div class="alert alert-success" role="alert">  Application has been approved!    </div>
              </c:if>
              <c:if test="${not_approv ne null}">
                     <div class="alert alert-warning" role="alert">  Application  rejected!    </div>
</c:if>

<c:if test="${listWfp ne null}">

         <table class="table table-bordered">
             <tr>
                <th>brandName</th>
                <th>modelName</th>
                <th>priceOnDay</th>
                <th>regNumber</th>
                <th>firstName</th>
                <th>lastName</th>
                <th>ordersStatus</th>
                <th>rentStartDate</th>
                <th>rentEndDate</th>
                <th> </th>
                <th> </th>
             </tr>
             <c:forEach items="${listWfp}" var="ord">
                <form action="/rentcar/adminReturn">
                 <tr>
                     <input type="hidden" name="ordId" value="${ord.id}" />
                     <input type="hidden" name="carId" value="${ord.carId}" />
                     <td><c:out value="${ord.brandName}"></c:out></td>
                     <td><c:out value="${ord.modelName}"></c:out></td>
                     <td><c:out value="${ord.priceOnDay}"></c:out></td>
                     <td><c:out value="${ord.regNumber}"></c:out></td>
                     <td><c:out value="${ord.firstName}"></c:out></td>
                     <td><c:out value="${ord.lastName}"></c:out></td>
                     <td><c:out value="${ord.ordersStatus}"></c:out></td>
                     <td><c:out value="${ord.rentStartDate}"></c:out></td>
                     <td><c:out value="${ord.rentEndDate}"></c:out></td>
                     <td><input type="radio" id="dam1" name="damage" value="1">
                           <label for="dam1">Not damaged</label><br>
                           <input type="radio" id="dam2" name="damage" value="2">
                           <label for="dam2">Damaged</label><br>
                      </td>
                       <td> <div style="float: left; padding: 5px">
                           <button type="submit" class="btn btn-primary"> Rent PAID </button>  </div></td>
                 </tr>
                 </form>
             </c:forEach>
        </table>



</c:if>
 <c:if test="${not_damage ne null}">
                     <div class="alert alert-success" role="alert">  Order successfully paid!    </div>
                </c:if>
 <c:if test="${damage ne null}">
                       <div class="alert alert-warning" role="alert">  The order has been successfully paid,
                        add damage to the car to the database!    </div>
                </c:if>
 <c:if test="${demCar ne null}">
                    <div class="alert alert-dark" role="alert">  Damage to car ${demCar} has been added!    </div>
 </c:if>
<!---------------------------- ListDamage -->
 <c:if test="${not_dem ne null}">
                    <div class="alert alert-info" role="alert">  Damage list is empty!    </div>
 </c:if>
<!---------------------------- ListDamage -->
<c:if test="${carCreate ne null}">
                    <div class="alert alert-info" role="alert">  Car ${demCar} successfully created!    </div>
 </c:if>
 <c:if test="${not_car ne null}">
                     <div class="alert alert-info" role="alert">  Car LIST is empty!    </div>
  </c:if>
</body>
</html>