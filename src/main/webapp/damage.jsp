<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.viraz84.myrentcar.model.enam.OrdersStatus"%>
<%@include file="part/header.jsp"%>



<div class="container">
  <div class="row"> <p> Enter demage for a car: ${carDemage} </p>
</div>

  <form class="main_form"  action="/rentcar/adminReturn" method="post" >
                  <div class="container">
                      <hr>
                      <div class="col-md-6">
                          <div class="col-md-offset-3">
                              <div class="row">
                                      <label>Description:</label>
                                      <textarea name="description" cols="40" rows="3" required placeholder=
                                      "Enter description a demage"></textarea>
                              </div>
                          </div>
                          <div class="col-md-offset-3">
                              <div class="row">
                                      <label>Repair cost:</label>
                                      <input type="number" name="repair_cost" required placeholder="Enter repair cost a car">
                              </div>
                          </div> <br>
                          <div class="col-sm-offset-1 margin-top-">
                              <input class="btn btn-lg btn-success" type="submit" value="Enter information">
                          </div> <br><br>
  </form>

</body>
</html>