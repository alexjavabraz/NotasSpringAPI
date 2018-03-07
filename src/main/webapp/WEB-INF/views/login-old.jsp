<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login ZCO2</title>

    <!-- Bootstrap core CSS -->
    <link href="<spring:url value='resources/<spring:url value='resources/vendor/bootstrap/css/bootstrap.min.css'/>'/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<spring:url value='resources/css/certificado.css'/>" rel="stylesheet">
    <link href="<spring:url value='resources/css/login.css'/>" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

  </head>

<body class="loginBody">
      <!-- Page Content -->
    <div class="container">
      <div class="row top">
        <div class="col-6 col-md-3"> </div>
          <div class="col-6 col-md-6">
            <div class="col-md-offset-12 col-md-12">
            	<form id="teste" action="/login" method="post"> 
                <div class="form-login box">
                  <div class=" col-md-12 text-center img1">
                   <img class="img-responsive" src="<spring:url value='resources/img/Filhote_Branco.png'/>" alt="Imagem" width="80" />
                  </div>   
                  <h4 class="altura">Bem vindo</h4>
                  <div class=" col-md-12 text-center">
                    
                    <input type="text" name="usuario" id="userName" class="form-control input-sm chat-input tamanho" placeholder="Login" style="background-image: url(<spring:url value='resources/img/user.png'/>)!important;" maxlength="30"/>
                    </br>
                    <input type="password" name="senha" id="userPassword" class="form-control input-sm chat-input tamanho" placeholder="Senha" style="background-image: url(<spring:url value='resources/img/password.png'/>)!important;" maxlength="9" />
                    </br>
                 
                  </div>
                  <div class="wrapper bottom">
                    <span class="group-btn">     
                        <button type="submit" class="btn btn-info" disabled="disabled">Entrar <i class="fa fa-sign-in"></i></button>
                    </span>
                  </div>
                </div>
                </form>
            </div>
          </div>
        <div class="col-6 col-md-3"> </div>
      </div>

      <!-- /.container -->
    </div>


      <!-- Bootstrap core JavaScript -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <script src="<spring:url value='resources/<spring:url value='resources/vendor/jquery/jquery.min.js'/>'/>"></script>
      <script src="<spring:url value='resources/<spring:url value='resources/vendor/bootstrap/js/bootstrap.bundle.min.js'/>'/>"></script>
      <script type="text/javascript" src="<spring:url value='resources/primeui/jquery-ui.js'/>"></script>
      <script type="text/javascript" src="<spring:url value='resources/primeui/primeui.min.js'/>"></script>
      <script type="text/javascript" src="<spring:url value='resources/js/login.js'/>"></script>
  </body>

</html>
