<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.viraz84.myrentcar.model.enam.OrdersStatus"%>
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
<c:if test="${allDamage ne null}">
    <div class="container">
         <table class="table table-bordered">
             <tr>
                 <th>brandName</th>
                 <th>modelName</th>
                 <th>regNumber</th>
                 <th>description</th>
                 <th>repairCost</th>
                 <th>Damage status</th>
                 <th></th>
                 <th></th>
             </tr>
             <c:forEach items="${allDamage}" var="cardem">
                <form action="/rentcar/damageList" method="post">
                 <tr>
                     <input type="hidden" name="demId" value="${cardem.id}" />
                     <input type="hidden" name="ordIdDem" value="${cardem.ordersId}" />
                     <td><c:out value="${cardem.brandName}"></c:out></td>
                     <td><c:out value="${cardem.modelName}"></c:out></td>
                     <td><c:out value="${cardem.regNumber}"></c:out></td>
                     <td><c:out value="${cardem.description}"></c:out></td>
                     <td><c:out value="${cardem.repairCost}"></c:out></td>
                     <td><c:out value="${cardem.damageStatus}"></c:out></td>
                     <td><input type="radio" id="damg1" name="damageCar" value="1">
                           <label for="damg1">Car FIXED</label><br>
                           <input type="radio" id="damg2" name="damageCar" value="2">
                           <label for="damg2">Сar Scrapped</label><br>
                      </td>
                       <td> <div style="float: left; padding: 5px">
                           <button type="submit" class="btn btn-primary"> SAVE </button>  </div></td>
                 </tr>
                 </form>
             </c:forEach>
        </table>
  </div>

</c:if>
<c:if test="${not_dem ne null}">
                    <div class="alert alert-info" role="alert">  Damage list is empty!    </div>
 </c:if>

 <c:if test="${del_car ne null}">
                     <div class="alert alert-danger" role="alert">  Car with id ${del_car} deleted!    </div>
  </c:if>

  <c:if test="${fix_car ne null}">
                      <div class="alert alert-success" role="alert">  Car with id ${fix_car} repaired!    </div>
   </c:if>

</body>
</html>