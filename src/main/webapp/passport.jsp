<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="part/header.jsp"%>

    <div class="container text-center">
            <div class="container">
                  <div class="text-info text-center">
                    <h4><li class="nav-item">Your CAR: ${sessionScope.carById.brandName} ${sessionScope.carById.modelName}
                    for ${sessionScope.carById.priceOnDay} $ on day  ${sessionScope.carById.regNumber}</li></h4>
                   </div>
                <div class="text-info text-center">
                    <h3>Enter passport</h3>
                </div>
                <form class="main_form"  action="/rentcar/passp" >
                    <div class="container">
                        <hr>
                        <div class="col-md-12">
                            <div class="col-md-offset-3">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <label>PASSPORT NUMBER :</label></div>
                                    <div class="col-xs-3">
                                        <input type="text" name="passNumber" required placeholder="Enter your passport Number">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-offset-3">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <label>ISSUER ROVD :</label></div>
                                    <div class="col-xs-3">
                                        <input type="text" name="issuerRovd" required placeholder="Enter your issuerRovd">
                                    </div>
                                </div>
                            </div><br><br>

                            <div class="col-md-offset-3">
                                   <div class="row">
                                       <div class="col-xs-4">
                                           <label>Car rental start date :</label></div>
                                               <div class="col-xs-3">
                                                    <input type="date" name="startRent" required placeholder="Enter start date">
                                             </div>
                                    </div>
                             </div>

                                 <div class="col-md-offset-3">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <label>Car rental end date :</label></div>
                                                <div class="col-xs-3">
                                                     <input type="date" name="endRent" required placeholder="Enter end date">
                                              </div>
                                      </div>
                                 </div><br>

                            <div class="col-sm-offset-1 margin-top-5">
                                <input class="btn btn-lg btn-success" type="submit" value="Enter passport">
                            </div>

                        </div>

                    </div>
                </form>
            </div>
    </div>

</body>
</html>
