<html>
<head>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
        <title>Rent Car</title>
</head>
<body>
 <div class="collapse navbar-collapse" id="navbarsExample04">

 		<c:if test="${sessionScope.user eq null}">
 		    <li class="nav-item"><a class="nav-link"
 				href="<c:url value = "/registration.jsp"/>">Register</a> </li>
 				<li class="nav-item">
 				<a class="nav-link" href="<c:url value = "/login.jsp"/>">Login</a>
 				</li>
 		</c:if>
        <c:if test="${sessionScope.user ne null}">
            <ul>
                <h3><li class="nav-item">Hello ${sessionScope.user.firstName} !</li></h3>
            </ul>
            <ul class="nav nav-pills">
				<li class="nav-item"><a class="btn btn-warning btn-lg" href="<c:url value = "/logout"/>">Logout</a></li>
			</ul><br>
		</c:if>
		<c:if test="${sessionScope.user ne null && UserRole.ADMIN eq sessionScope.user.userRole}">
			<ul class="nav nav-pills">
			    <li class="nav-item"><a class="btn btn-danger btn-lg" href="<c:url value = "/damageList"/>"> Damage List </a></li>
			    <li class="nav-item"><a class="btn btn-success btn-lg" href="<c:url value = "/addCar.jsp"/>"> Add Car </a></li>
  			    <li class="nav-item"><a class="btn btn-info btn-lg" href="<c:url value = "/carList"/>"> List Car </a></li>
			</ul>
 		</c:if>
</div> <br>