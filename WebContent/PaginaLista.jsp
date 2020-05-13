<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%@page import="aplicacion.modelo.pojo.Producto" %>
<%@page import="aplicacion.modelo.pojo.ProductoSinPrecio" %>
<%!ArrayList<Producto> productos; %>
<%!ArrayList<ProductoSinPrecio> productosSinPrecio; %>
<%!Usuario usuario = null; %>
<%!String nombreLista = null; %>
<!DOCTYPE html>
<html lang="es">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>PriceStalker</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

	<%
		productos = (ArrayList<Producto>) request.getAttribute("productos");
		productosSinPrecio = (ArrayList<ProductoSinPrecio>) request.getAttribute("productosSinPrecio");
		usuario = (Usuario) request.getAttribute("usuario");
		nombreLista = (String) request.getAttribute("nombreLista");
		String opcionAddProducto = "Añadir producto a la lista";
		if(nombreLista == null){
			opcionAddProducto = "Crear lista de productos";
		}
	%>

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand-sm navbar-light bg-white  mb-4 static-top shadow">
          <a class="nav-brand mr-auto" href="Principal"><img alt="Logo" src="imgs/PriceStalkerLogoB1_transparentRecortado.png" class="" style="max-width: 190px"></a>
          <%
          if(usuario != null){
          %>
          <button class="navbar-toggler mx-auto" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav ml-auto">
              <li class="nav-item">
                  <a class="nav-link text-dark text-center" href="AddProducto"><%=opcionAddProducto %></a>
              </li>

              <li class="topbar-divider d-none d-sm-block"></li>

              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle text-dark text-center" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <%=usuario.getNombre() %>
                </a>
                <!-- Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                  <a class="dropdown-item" href="AdminUser">
                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                    Administrar usuario
                  </a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Cerrar sesión
                  </a>
                </div>
              </li>
            </ul>
          </div>
          <%} %>
        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">
			<!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class=" mb-0 text-gray-800">Tu lista de productos</h1>
          </div>
		<%
		if(productos != null | productosSinPrecio != null){
			if(!productos.isEmpty() | !productosSinPrecio.isEmpty()){
				
				%>
          <h1 class="h3 text-gray-800"><%=nombreLista %></h1>

          <div class="row">
<%
				if(!productosSinPrecio.isEmpty()){
					for(ProductoSinPrecio productoSinPrecio : productosSinPrecio){
						String enlace = productoSinPrecio.getLink();
						if(enlace.equals("ProductoNotFound")){
							enlace += "?idContenido="+productoSinPrecio.getId();
						}
						%>
						
						<div class="col-lg-4 col-md-6">
                <!-- Dropdown Card Example -->
              <div class="card shadow mb-4">
                  <!-- Card Body -->
                <div class="card-body">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col-xl-7 col-lg-12 col-sm-12">
                                <img alt="Imágen del producto" class="mx-auto d-block w-100" src="<%=productoSinPrecio.getImgLink()%>">
                            </div>
                            <div class="col-xl-5 col-lg-12 col-sm-12 d-flex align-items-center text-center">
                                <div class="mx-auto">
                                <%if(!productoSinPrecio.getNombre().equals("El enlace de este producto no funciona.")){ %>
                                	<div class="text-dark mb-4"><h5>Hasta que escaneemos el producto desconocemos el precio actual</h5></div>
								<%} %>
                                    <div class="text-dark mt-4"><h4>Precio objetivo: <span class="text-nowrap"><%=String.format("%.2f",productoSinPrecio.getPrecioObjetivo())%> €</span></h4></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary"><a href="<%=enlace%>"><%=productoSinPrecio.getNombre()%></a></h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink1">
                      <div class="dropdown-header">Opciones:</div>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="Eliminar?producto=<%=productoSinPrecio.getId()%>">Eliminar de la lista</a>
                    </div>
                  </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
						<%
					}
				}
				if(!productos.isEmpty()){
					for(Producto producto : productos){
						%>
						
						<div class="col-lg-4 col-md-6">
                <!-- Dropdown Card Example -->
              <div class="card shadow mb-4">
                  <!-- Card Body -->
                <div class="card-body">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <div class="col-xl-7 col-lg-12 col-sm-12">
                                <img alt="Imágen del producto" class="mx-auto d-block w-100" src="<%=producto.getImgLink()%>">
                            </div>
                            <div class="col-xl-5 col-lg-12 col-sm-12 d-flex align-items-center text-right">
                                <div class="mx-auto">
                                <%if(!producto.getCoste().equals(Double.valueOf("-1"))){ %>
                                <div class="text-dark mt-4"><h4>Precio actual: <span class="text-nowrap"><%=String.format("%.2f",producto.getCoste())%> €</span></h4></div>
								<%}else{ %>
								<div class="text-dark mb-4"><h4>Producto no disponible</h4></div>
								<%} %>
                                    <div class="text-dark mt-4"><h4>Precio objetivo: <span class="text-nowrap"><%=String.format("%.2f",producto.getPrecioObjetivo())%> €</span></h4></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary"><a href="<%=producto.getLink()%>"><%=producto.getNombre()%></a></h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink1">
                      <div class="dropdown-header">Opciones:</div>
                      <%if(!producto.getCoste().equals(Double.valueOf("-1"))){ %>
                      <a class="dropdown-item" href="Grafica?producto=<%=producto.getId()%>"><i class="fas fa-fw fa-chart-area text-gray-400"></i>Historial de precios</a>
                      <%} %>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="Eliminar?producto=<%=producto.getId()%>">Eliminar de la lista</a>
                    </div>
                  </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
						<%
					}	
				}
				%>
          </div>
      <!-- End of Main Content -->

		<%
			} else{%>
				<!-- 404 Error Text -->
          <div class="text-center">
              <div class="error mx-auto text-left" style="width: 400px" data-text="...¡Ups!">...¡Ups!</div>
              <h3>No tienes ninguna lista de productos</h3>
            <div class="col-lg-5 mx-auto"><img class="img-fluid px-3 px-sm-4 mt-3 mb-4" src="imgs/undraw_blank_canvas_3rbb.png" alt=""></div>
            <p class="lead text-gray-800">Esta cuenta de usuario no tiene asociada ningúna lista de productos todavía.</p>
            <p class="lead text-gray-800">Si es tu primera vez aquí no hay nada de que preocuparse.</p>
            <p class="text-gray-800 mb-0">Crea tu primera lista de productos en esta cuenta de usuario.</p>
            <a href="AddProducto">Crear una lista de productos &rarr;</a>
            <br>
            <a href="Principal">&larr; Volver a la página principal</a>
          </div>
          </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->
			<%}
		}
	%>

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Price Stalker 2020</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">¿Quieres salir?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Selecciona "Cerrar sesión" si quieres terminar la sesión.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Mantener la sesión abierta</button>
          <a class="btn btn-primary" href="Logout">Cerrar sesión</a>
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
