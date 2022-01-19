<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.viraz84.myrentcar.model.enam.OrdersStatus"%>
<%@ page import="by.viraz84.myrentcar.model.enam.CarStatus"%>
<%@ page import="by.viraz84.myrentcar.model.enam.UserRole"%>
<%@include file="part/header.jsp"%>

<div class="collapse navbar-collapse" id="navbarsExample04">
		<c:if test="${sessionScope.user ne null && UserRole.ADMIN eq sessionScope.user.userRole}">

            <ul class="nav nav-pills">
			    <li class="nav-item"><a class="btn btn-danger btn-lg" href="<c:url value = "/admin"/>"> Back </a></li>
			</ul>
 		</c:if>
 	</div>

</div> <br>
<c:if test="${allCar ne null}">
    <div class="container">
         <table class="table table-bordered">
             <tr>
                 <th>brandName</th>
                 <th>modelName</th>
                 <th>priceOnDay</th>
                 <th>regNumber</th>
                 <th>Status</th>
                 <th></th>

             </tr>
             <c:forEach items="${allCar}" var="car">
                <form action="/rentcar/carList" method="post">
                 <tr>
                     <input type="hidden" name="carId" value="${car.id}" />

                     <td><c:out value="${car.brandName}"></c:out></td>
                     <td><c:out value="${car.modelName}"></c:out></td>
                      <td><c:out value="${car.priceOnDay}"></c:out></td>
                     <td><c:out value="${car.regNumber}"></c:out></td>
                     <td><c:out value="${car.carStatus}"></c:out></td>
                     <c:if test="${car.carStatus ne CarStatus.TAKEN && UserRole.ADMIN eq sessionScope.user.userRole}">
                       <td> <div style="float: left; padding: 5px">
                           <button type="submit" class="btn btn-primary"> DELETE </button>  </div></td>
                     </c:if>

                 </tr>
                 </form>
             </c:forEach>
        </table>
  </div>

</c:if>


 <c:if test="${del_car ne null}">
                     <div class="alert alert-danger" role="alert">  Car with id ${del_car} deleted!    </div>
  </c:if>

  <c:if test="${fix_car ne null}">
                      <div class="alert alert-success" role="alert">  Car with id ${fix_car} repaired!    </div>
   </c:if>

</body>
</html>