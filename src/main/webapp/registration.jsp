<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Register</title>
</head>
<body>

<div class="registr">
    <div class="container text-center">
        <div class="container">

            <div class="text-info text-center">
                <h3>Registration new User</h3>
            </div>
            <form class="main_form"  action="/rentcar/register" >
                <div class="container">
                    <hr>
                    <div class="col-md-12">
                        <div class="col-md-offset-3">
                            <div class="row">
                                <div class="col-xs-4">
                                    <label>Login :</label></div>
                                <div class="col-xs-3">
                                    <input type="text" name="login" required placeholder="Enter your login">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3">
                            <div class="row">
                                <div class="col-xs-4">
                                    <label>Password :</label></div>
                                <div class="col-xs-3">
                                    <input type="password" name="password" required placeholder="Enter your Password">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3">
                            <div class="row">
                                <div class="col-xs-4">
                                    <label >First Name :</label></div>
                                <div class="col-xs-3">
                                    <input type="text" name="fname" required placeholder="Enter your Name">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-offset-3">
                            <div class="row">
                                <div class="col-xs-4">
                                    <label>Last Name :</label></div>
                                <div class="col-xs-3">
                                    <input type="text" name="lname"
                                           required placeholder="Enter your Surname">
                                </div>
                            </div>
                        </div>
                        <br>

                        <div class="col-sm-offset-1 margin-top-5">
                            <input class="btn btn-lg btn-success" type="submit" value="Register">
                        </div> <br><br>

                    </div>
                    <a href="/rentcar" class="btn btn-info btn-lg"> Back to home page </a>
                </div>
            </form>
        </div>
    </div>
</div>




</body>
</html>