<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String error; %>
<!DOCTYPE html>
<html lang="es">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Price Stalker</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-12">
            <div class="p-5">
              <div class="text-center">
                <h1 class="text-gray-900 mb-4">Eliminar cuenta</h1>
              </div>
              <form class="user" action="DelUser" method="POST">
                  <div class="text-center mb-3">
                      <div class="medium">¿Estás seguro que quieres eliminar tu cuenta? Se borrarán todos tus datos...</div>
                  </div>
                <div class="form-group row">
                  <div class="col-sm-12 mb-3 mb-sm-0">
                      <input name="password" type="password" class="form-control form-control-user" placeholder="Contraseña" required="">
                  </div>
                </div>
                <button class="btn btn-primary btn-user btn-block" type="submit">
                  Eliminar usuario
                </button>
                <%
                error = (String) request.getAttribute("error");
		  		if(error != null){
                %>
                <hr>
                <div class="text-center">
                    <h4 class="text-danger mb-4">
                          <%=error %>
                    </h4>
                </div>
                <%} %>
              </form>
              <hr>
              <div class="text-center">
                <a class="small" href="Principal">Volver a la página principal</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

</body>

</html>
