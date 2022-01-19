<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.viraz84.myrentcar.model.enam.UserRole"%>
<%@include file="part/header.jsp"%>

   <div class="collapse navbar-collapse" id="navbarsExample04">
		<c:if test="${sessionScope.user ne null && UserRole.ADMIN eq sessionScope.user.userRole}">

            <ul class="nav nav-pills">
			    <li class="nav-item"><a class="btn btn-danger btn-lg" href="<c:url value = "/admin"/>"> Back </a></li>
			</ul>
 		</c:if>
 	</div>
</div>
<! ------------------------------------------------------------------------->
<br>
<div class="col-md-12">
				<form class="main_form " action="/rentcar/addCar">
					<div class="row-md-8 col-xl-6">
						<div class="col-md-offset-3">
						    <div class="col-xs-2">  <label>Car brand:</label></div>
							    <input class="form_contril" placeholder="brand" type="text" name="brand" >
						    </div>
						</div><br>

					<div class="row-md-8 col-xl-6">
                     	<div class="col-md-offset-3">
                     		<div class="col-xs-2">  <label>Car model:</label></div>
					    		<input class="form_contril" placeholder="model" type="text" name="model">
						    </div>
						</div><br>

                    <div class="row-md-8 col-xl-6">
                        <div class="col-md-offset-3">
                     		<div class="col-xs-2">  <label>Price per rental day:</label></div>
					    		<input class="form_contril" placeholder="price per day" type="number" name="price_day">
						    </div>
						</div><br>

				    <div class="row-md-8 col-xl-6">
                         <div class="col-md-offset-3">
                           		<div class="col-xs-2">  <label>Сar registration number:</label></div>
                                	<input class="form_contril" placeholder="reg number" type="text" name="reg_numb">
                                </div>
                        </div><br>

						<div class="col-sm-offset-5 margin-top-">
                                 <input class="btn btn-lg btn-success" type="submit" value="Create car">
                        </div
					</div>
				</form>
			</div>
</body>
</html>