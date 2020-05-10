<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String nombreLista = null; %>
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

	<%
		nombreLista = (String) request.getAttribute("nombreLista");
		%>

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">
                    <%
                    	if(nombreLista != null){
                    		out.print("Añadir producto en la lista: " + nombreLista);
                    	} else{
                    		out.print("Crear una lista de productos");
                    	}
                    %>
                    </h1>
                  </div>
                    <form class="user" action="AddProducto" method="POST">
                    <%
                    	if(nombreLista == null){
                    %>
                    <div class="form-group">
                        <input name="nombreLista" type="text" class="form-control form-control-user" placeholder="Nombre de la lista" required="">
                    </div>
                    <%} %>
                    <div class="form-group">
                        <input name="enlace" type="text" class="form-control form-control-user" placeholder="Copia aquí el enlace del producto" required="">
                    </div>
                    <div class="form-group">
                        <input name="precioObjetivo" type="number" step="0.01" min="0.01" class="form-control form-control-user" placeholder="Introduce el precio objetivo" required="">
                    </div>
                    <button type="submit" class="btn btn-primary btn-user btn-block">Añadir producto a mi lista</button>
                    <hr>
                  </form>
                  <hr>
                  <div class="text-center mt-5">
                    <div class="medium">¡Cuando el producto baje de precio por debajo del precio objetivo te mandaremos un correo!</div>
                  </div>
                  <div class="text-center mt-3">
                    <a class="medium" href="Lista">Volver a tu lista de la compra</a>
                  </div>
                  <div class="text-center mt-3">
                    <a class="medium" href="Principal">Volver a la página principal</a>
                  </div>
                </div>
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
