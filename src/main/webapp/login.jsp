<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</head>
<body>

<div class="login">
    <div class="container text-center">
        <div class="container">
            <h3>Enter Login and Password</h3><br>
            <form  class="main_form" action="/rentcar/login">

                <input type="text" required placeholder="login" name="login"><br>
                <input type="password" required placeholder="password" name="password"><br><br>
                <button class="btn btn-lg btn-primary" type="submit">Login</button>
                <a href="/rentcar/registration.jsp" class="btn btn-info btn-lg"> Register </a> <br><br>
                <a href="/rentcar" class="btn btn-info btn-lg"> Back to home page </a>
                <c:if test="${not_found ne null}">
                                      <div class="alert alert-warning" role="alert">  User not found!    </div>
                   </c:if>
            </form>

        </div>
    </div>
</div>


</body>
</html>