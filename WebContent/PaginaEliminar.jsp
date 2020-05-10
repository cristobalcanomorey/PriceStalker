<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.modelo.pojo.ProductoSinPrecio" %>
<%!Usuario usuario = null; %>
<%!ProductoSinPrecio producto = null; %>
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
		producto = (ProductoSinPrecio) request.getAttribute("producto");
		usuario = (Usuario) request.getAttribute("usuario");
	%>
  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
            	<%if(producto != null){ %>
                <div class="col-lg-6 d-none d-lg-block h-100"><img src="<%=producto.getImgLink() %>" alt="imágen del producto" class="w-100 m-2"></div>
                <%} %>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Eliminar producto de tu lista</h1>
                  </div>
                  <%
                  if(producto != null){
                  %>
                  <form class="user" action="Eliminar" method="POST">
                    <div class="h-100 col-lg-12 d-none d-sm-block d-lg-none">
                        <img src="<%=producto.getImgLink() %>" alt="Imágen del producto" class="w-100 mt-1 mb-1">
                    </div>
                    <p class="mb-4">Nombre: <a href="<%= producto.getLink()%>"><%=producto.getNombre() %></a></p>
                    <p>¿Seguro que quieres eliminar este producto de tu lista?</p>
                    <button type="submit" class="btn btn-primary btn-user btn-block">Eliminar producto de mi lista</button>
                    <a href="Lista" class="btn btn-danger btn-user btn-block">Cancelar</a>
                    <input type="hidden" name="idContenido" value="<%=producto.getId() %>">
                  </form>
                  <%} %>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="Principal">Volver a la página principal</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="Lista">Volver a la lista de productos</a>
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
