<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Inicio</title>
  <!-- Bootstrap core CSS-->
  <link href="<spring:url value='resources/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="<spring:url value='resources/vendor/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="<spring:url value='resources/css/sb-admin.css'/>" rel="stylesheet">
</head>

<body class="bg-dark">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form id="frmLogin" action="/login" method="post"> 
          <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input class="form-control" name="userName" id="userName" type="email" aria-describedby="emailHelp" placeholder="Enter email">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input class="form-control" name="password" id="password" type="password" placeholder="Password">
          </div>
          <div class="form-group">
            <div class="form-check">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember Password</label>
            </div>
          </div>
          <button type="submit" class="btn btn-info">Entrar <i class="fa fa-sign-in"></i></button>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="register.html">Register an Account</a>
          <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
          
			  <c:if test="${not empty sessionScope.message}">
			    <span style="color:green"><c:out value="${sessionScope.message}"/></span>
			    <c:remove var="message" scope="session" />
			  </c:if>
          
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript-->
  <script src="<spring:url value='resources/vendor/jquery/jquery.min.js'/>"></script>
  <script src="<spring:url value='resources/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
  <!-- Core plugin JavaScript-->
  <script src="<spring:url value='resources/vendor/jquery-easing/jquery.easing.min.js'/>"></script>
</body>

</html>
