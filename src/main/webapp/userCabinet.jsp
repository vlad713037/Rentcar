<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="part/header.jsp"%>

<form class="main_form" action="/rentcar/cabinet"  >
        <table class="table table-bordered">
            <tr>
                <th>brandName</th>
                <th>modelName</th>
                <th>priceOnDay</th>
                <th>regNumber</th>
                <th><p>Your choose</p></th>
            </tr>

            <c:forEach items="${allCarFree}" var="car">
                <tbody>
                <tr>
                    <td><c:out value="${car.brandName}"></c:out></td>
                    <td><c:out value="${car.modelName}"></c:out></td>
                    <td><c:out value="${car.priceOnDay}"></c:out></td>
                    <td><c:out value="${car.regNumber}"></c:out></td>

                    <td><p><input type="radio" name="car" value="${car.id}"></p></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <div class="text-center">
            <input class="btn btn-warning btn-padding-size" type="submit" value="Your car">
        </div>
    </form>


</body>
</html>
