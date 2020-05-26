<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="aplicacion.modelo.pojo.Usuario" %>
<%!Usuario usuario = null; %>
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
		usuario = (Usuario) request.getAttribute("usuario");
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
          <button class="navbar-toggler mx-auto" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav ml-auto">
            <%
            if(usuario != null){
            %>
              <li class="nav-item">
                  <a class="nav-link text-dark text-center" href="Lista">Tu lista de productos</a>
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
              <%}else{ %>
              <li class="nav-item">
                  <a class="nav-link text-dark text-center" href="Registro">Registrarse</a>
              </li>
              <li class="nav-item">
                  <a class="nav-link text-dark text-center" href="Login">Iniciar sesión</a>
              </li>
              <%} %>
            </ul>
          </div>
        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class=" mb-0 text-gray-800">¡Bienvenido!</h1>
          </div>
          
          <div class="row">

            <div class="col-lg-6 mb-4">
              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Introducción</h6>
                </div>
                <div class="card-body text-dark" style="min-height: 691.6px">
                  <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;" src="imgs/undraw_welcome_cats_thqn.png" alt="Imágen de bienvenida">
                  </div>
                  <p>¡Bienvenido a Price Stalker! ¿Estás preparado para ahorrar dinero?</p>
                  <p>Price Stalker es el servicio web que te ayudará a ahorrar dinero en la compra online de la forma más sencilla... Esperando...</p>
                  <p>Los productos de las tiendas online tienden a subir y bajar de precio según la oferta, la demanda y las promociones que puedan surgir. Solo hay que ir cada día a la tienda y mirar si ha bajado de precio... ¿Pudiendo esperar a que bajen de precio para qué gastar de más? Pero todos tenemos mejores cosas que hacer que mirar cada día un producto a ver si ha bajado de precio o no... ¿No?</p>
                  <p>¡Pues nosotros no! ¡Nos encargamos de vigilar cada día el precio del producto por tí!</p>
                  <p>La sencilla funcionalidad de PriceStalker permite a cualquiera adentrarse en el maravilloso mundo del seguimiento de precios automatizado. Tan solo creando una cuenta de usuario y copiando los enlaces del producto en nuestra página ya podrás monitorizar tus productos de varias tiendas online en una misma página y recibir notificaciones cuando algún producto de tu lista baje de precio por debajo del umbral establecido.</p>
                  <p class="mb-0">¿No es tu primera vez aquí?</p>
                  <a href="Login">Inicia sesión &rarr;</a>
                </div>
              </div>
            </div>
              
            <div class="col-lg-6 mb-4">
              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Mini tutorial</h6>
                </div>
                <div class="card-body text-dark" style="min-height: 691.6px">
                  <p class="mb-0">Lo primero es crear una cuenta de usuario introduciendo tu nombre de usuario, un correo al que quieras recibir las alertas cuando algún producto baje de precio y una contraseña segura.</p>
                  <p><a href="Registro">Empecemos &rarr;</a></p>
                  <p>Una vez creada tu cuenta de PriceStalker ya tienes acceso total a nuestros servicios. El siguiente paso es crear una lista de productos. ¡Así es! ¡En Plural!</p>
                  <p>Copia el enlace del primer producto que quieres que vigilemos y pegalo en nuestra página. La primera véz te pedirá que escribas el nombre de tu lista. Puedes poner un nombre genérico como "lísta de la compra", uno más específico como "Portátiles", o "adfjaskkjas" si tienes prisa.</p>
                  <p class="mb-0">También te pedirá el precio "objetivo". Este es el précio al que te gustaría comprar el producto. El día que el producto que quieres tenga este precio o menos te mandaremos un correo para avisarte.</p>
                  <%if(usuario != null){ %>
                  <p><a href="AddProducto">Crea tu lista de productos &rarr;</a></p>
                  <p><a href="AddProducto">¿Ya tienes una? Añade otro producto &rarr;</a></p>
                  <%} %>
                  <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4" style="width: 25rem;" src="imgs/undraw_web_search_eetr.png" alt="Imágen de escaneo">
                  </div>
                  <p class="mb-0">Ahora solo falta esperar. ¡Grácias por utilizar nuestros servicios!</p>
                </div>
              </div>
            </div>

          </div>
      <!-- End of Main Content -->

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
